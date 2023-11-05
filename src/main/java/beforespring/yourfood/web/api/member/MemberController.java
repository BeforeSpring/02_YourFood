package beforespring.yourfood.web.api.member;
import beforespring.yourfood.app.member.service.MemberService;
import beforespring.yourfood.web.api.common.StatusCode;
import beforespring.yourfood.web.api.member.request.UpdateUserSettingsRequest;
import beforespring.yourfood.web.api.common.GenericResponse;
import beforespring.yourfood.web.api.member.response.UpdateUserSettingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    /**
     * 회원 설정 업데이트
     *
     * @param updateUserSettingsRequest 업데이트 정보
     */
    @PatchMapping("/settings")
    public GenericResponse<UpdateUserSettingResponse> updateUserSettings(@RequestBody UpdateUserSettingsRequest updateUserSettingsRequest) {
        memberService.updateLunchRecommendationConsent(updateUserSettingsRequest.lunchRecommendationConsent(), updateUserSettingsRequest.memberId());
        memberService.updateLocation(updateUserSettingsRequest.lat(), updateUserSettingsRequest.lon(), updateUserSettingsRequest.memberId());

        return GenericResponse.<UpdateUserSettingResponse>builder()
            .statusCode(StatusCode.CREATED)
            .message("Success")
            .build();
    }
}
