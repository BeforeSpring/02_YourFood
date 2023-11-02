package beforespring.yourfood.app.member.service;

import beforespring.yourfood.app.member.domain.Member;
import beforespring.yourfood.app.member.domain.MemberRepository;
import beforespring.yourfood.app.member.service.dto.CreateMemberDto;
import beforespring.yourfood.auth.authmember.domain.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    @Override
    public void updateLunchRecommendation(boolean lunchRecommendationConsent) {

    }

    @Override
    public void updateLocation(String lat, String lon) {

    }

    @Override
    public Long createMember(CreateMemberDto createMemberDto) {
        Member member = Member.builder()
            .username(createMemberDto.username()).build();
        memberRepository.save(member);
        return member.getId();
    }


}
