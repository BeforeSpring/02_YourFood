package beforespring.yourfood.app.member.service.dto;

import lombok.Builder;

import java.time.LocalDateTime;
public record CreateMemberDto(String username,
                              boolean lunchNotiStatus,
                              LocalDateTime notiAgreedAt
                              ) {
    @Builder
    public CreateMemberDto(String username, boolean lunchNotiStatus, LocalDateTime notiAgreedAt) {
        this.username = username;
        this.lunchNotiStatus = lunchNotiStatus;
        this.notiAgreedAt = notiAgreedAt;
    }
}
