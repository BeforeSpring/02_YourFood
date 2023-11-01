package beforespring.yourfood.web.api.member;

import beforespring.yourfood.web.request.member.LoginRequest;
import beforespring.yourfood.web.request.member.SignupRequest;
import beforespring.yourfood.web.request.member.UpdateUserSettingsRequest;
import beforespring.yourfood.web.response.GenericResponse;
import beforespring.yourfood.web.response.member.LoginResponse;
import beforespring.yourfood.web.response.member.SignupResponse;
import beforespring.yourfood.web.response.member.UpdateUserSettingResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {
    /**
     * 새로운 회원 가입
     * @param signupRequest 회원가입 정보
     * @return
     */
    @PostMapping
    public GenericResponse<SignupResponse> registerMember(@RequestBody SignupRequest signupRequest) {
        return null;
    }

    /**
     * 회원 로그인
     * @param loginRequest 로그인 정보
     * @return
     */
    @PostMapping("/login")
    public GenericResponse<LoginResponse> loginMember(@RequestBody LoginRequest loginRequest) {
        return null;
    }

    /**
     * 회원 설정 업데이트
     * @param updateUserSettingsRequest 업데이트 정보
     * @return
     */
    @PatchMapping("/settings")
    public GenericResponse<UpdateUserSettingResponse> updateUserSettings(@RequestBody UpdateUserSettingsRequest updateUserSettingsRequest) {
        return null;
    }
}
