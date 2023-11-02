package beforespring.yourfood.web.api.auth;

import beforespring.yourfood.auth.authmember.service.AuthMemberService;
import beforespring.yourfood.auth.authmember.service.dto.PasswordAuth;
import beforespring.yourfood.auth.authmember.service.dto.RefreshTokenAuth;
import beforespring.yourfood.auth.jwt.domain.AuthToken;
import beforespring.yourfood.web.api.common.GenericResponse;
import beforespring.yourfood.web.api.common.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthMemberService authMemberService;

    @PostMapping("/api/v1/member/auth")
    public GenericResponse<AuthToken> authWithUsernamePassword(@RequestBody PasswordAuth passwordAuth) {
        AuthToken authToken = authMemberService.authenticate(passwordAuth);
        GenericResponse genericResponse = GenericResponse.builder()
            .statusCode(StatusCode.OK)
            .message("Success")
            .data(authToken).
            build();
        return genericResponse;

    }

    @PostMapping("/api/v1/member/renew")
    public GenericResponse<AuthToken> authWithUsernamePassword(@RequestBody RefreshTokenAuth refreshTokenAuth) {
        AuthToken authToken = authMemberService.authenticate(refreshTokenAuth);
        GenericResponse genericResponse = GenericResponse.builder()
            .statusCode(StatusCode.OK)
            .message("success")
            .data(authToken)
            .build();
        return genericResponse;
    }
}
