package beforespring.yourfood.auth.authmember.service;

import beforespring.yourfood.app.member.service.MemberService;
import beforespring.yourfood.app.member.service.dto.CreateMemberDto;
import beforespring.yourfood.auth.jwt.domain.AuthToken;
import beforespring.yourfood.auth.jwt.service.JwtIssuer;
import beforespring.yourfood.auth.authmember.domain.Confirm;
import beforespring.yourfood.auth.authmember.domain.ConfirmRepository;
import beforespring.yourfood.auth.authmember.domain.AuthMember;
import beforespring.yourfood.auth.authmember.domain.AuthMemberRepository;
import beforespring.yourfood.auth.authmember.domain.PasswordHasher;
import beforespring.yourfood.auth.authmember.domain.TokenSender;
import beforespring.yourfood.auth.authmember.service.dto.ConfirmTokenDto.ConfirmTokenRequest;
import beforespring.yourfood.web.api.member.request.SignupMemberRequest;
import beforespring.yourfood.auth.authmember.service.dto.PasswordAuth;
import beforespring.yourfood.auth.authmember.service.dto.RefreshTokenAuth;
import beforespring.yourfood.auth.authmember.service.exception.ConfirmNotFoundException;
import beforespring.yourfood.auth.authmember.service.exception.AuthMemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthMemberServiceImpl implements AuthMemberService {

    private final JwtIssuer jwtIssuer;

    private final AuthMemberRepository authMemberRepository;
    private final ConfirmRepository confirmRepository;
    private final PasswordHasher passwordHasher;
    private final TokenSender tokenSender;
    private final TransactionTemplate transactionTemplate;
    private final MemberService memberService;

    @Override
    public Long join(SignupMemberRequest request) {
        String password = request.getPassword();

        // 비밀번호 검증 로직 추가
        validatePasswordPattern(password);

        String token = tokenSender.generateToken();
        Long memberId = transactionTemplate.execute(transactionStatus -> {
            AuthMember authMember = AuthMember.builder()
                .username(request.getUsername())
                .raw(request.getPassword())
                .hasher(passwordHasher)
                .build();
            authMemberRepository.save(authMember);
            Confirm confirm = Confirm.builder()
                .authMember(authMember)
                .token(token)
                .build();
            confirmRepository.save(confirm);


            CreateMemberDto createMemberDto = CreateMemberDto.builder()
                .username(request.getUsername()).build();

            memberService.createMember(createMemberDto);

            return authMember.getId();
        });

        tokenSender.sendEmail(request.getEmail(), token);

        return memberId;
    }

    @Override
    @Transactional
    public void joinConfirm(ConfirmTokenRequest request) {
        AuthMember authMember = authMemberRepository.findByUsername(request.getUsername()).orElseThrow(
            AuthMemberNotFoundException::new);
        Confirm confirm = confirmRepository.findByAuthMember(authMember).orElseThrow(
            ConfirmNotFoundException::new);
        authMember.verifyPassword(request.getPassword(), passwordHasher);
        confirm.verifyToken(request.getToken());
        authMember.joinConfirm();
    }

    @Override
    public AuthToken authenticate(PasswordAuth passwordAuth) {
        AuthMember authMember = authMemberRepository.findByUsername(passwordAuth.username())
            .orElseThrow(AuthMemberNotFoundException::new);
        authMember.verifyPassword(passwordAuth.password(), passwordHasher);

        return jwtIssuer.issue(authMember.getId(), authMember.getUsername());
    }

    @Override
    public AuthToken authenticate(RefreshTokenAuth refreshTokenAuth) {
        return jwtIssuer.renew(refreshTokenAuth.refreshToken(), refreshTokenAuth.username());
    }

    private void validatePasswordPattern(String password) {
        if (isConsecutiveCharsPattern(password)) {
            throw new IllegalArgumentException("동일한 문자를 3회 이상 연속으로 사용할 수 없습니다.");
        }
        if (!isComplexCharsPattern(password)) {
            throw new IllegalArgumentException("숫자, 문자, 특수문자 중 2가지 이상을 포함해야 합니다.");
        }
    }

    private boolean isComplexCharsPattern(String password) {
        String complexCharsPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{10,}$|" +
            "(?=.*[A-Za-z])(?=.*\\d).{10,}$|" +
            "(?=.*[A-Za-z])(?=.*[^A-Za-z\\d]).{10,}$|" +
            "(?=.*\\d)(?=.*[^A-Za-z\\d]).{10,}$";
        Matcher matcher = Pattern.compile(complexCharsPattern).matcher(password);
        return matcher.matches();
    }

    private boolean isConsecutiveCharsPattern(String password) {
        String consecutiveCharsPattern = "(.)\\1\\1";
        Matcher matcher = Pattern.compile(consecutiveCharsPattern).matcher(password);
        return matcher.find();
    }
}