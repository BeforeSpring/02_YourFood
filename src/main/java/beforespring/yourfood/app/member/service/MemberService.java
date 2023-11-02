package beforespring.yourfood.app.member.service;


import beforespring.yourfood.app.member.service.dto.CreateMemberDto;
import beforespring.yourfood.web.api.member.request.SignupMemberRequest;

public interface MemberService {
    void updateLunchRecommendation(boolean lunchRecommendationConsent);
    void updateLocation(String lat, String lon);
    Long createMember(CreateMemberDto createMemberDto);
}
