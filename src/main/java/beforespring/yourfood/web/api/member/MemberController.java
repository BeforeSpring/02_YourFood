package beforespring.yourfood.web.api.member;

import beforespring.yourfood.auth.authmember.service.AuthMemberService;
import beforespring.yourfood.web.api.common.StatusCode;
import beforespring.yourfood.web.api.member.request.SignupMemberRequest;
import beforespring.yourfood.web.api.member.request.LoginRequest;
import beforespring.yourfood.web.api.member.request.UpdateUserSettingsRequest;
import beforespring.yourfood.web.api.common.GenericResponse;
import beforespring.yourfood.web.api.member.response.LoginResponse;
import beforespring.yourfood.web.api.member.response.SignupResponse;
import beforespring.yourfood.web.api.member.response.UpdateUserSettingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private final AuthMemberService authMemberService;

    /**
     * 회원가입
     *
     * @param signupMemberRequest
     * @return
     */
    @PostMapping
    public GenericResponse<SignupResponse> registerMember(@RequestBody SignupMemberRequest signupMemberRequest) {
        Long memberId = authMemberService.join(signupMemberRequest);
        SignupResponse signupResponse = SignupResponse.builder()
            .id(memberId)
            .build();

        GenericResponse genericResponse = GenericResponse.builder()
            .statusCode(StatusCode.CREATED)
            .message("Success")
            .data(signupResponse).build();
        return genericResponse;

    }

    /**
     * 회원 로그인
     *
     * @param loginRequest 로그인 정보
     * @return
     */
    @PostMapping("/login")
    public GenericResponse<LoginResponse> loginMember(@RequestBody LoginRequest loginRequest) {
        return null;
    }

    /**
     * 회원 설정 업데이트
     *
     * @param updateUserSettingsRequest 업데이트 정보
     * @return
     */
    @PatchMapping("/settings")
    public GenericResponse<UpdateUserSettingResponse> updateUserSettings(@RequestBody UpdateUserSettingsRequest updateUserSettingsRequest) {
        return null;
    }
}
