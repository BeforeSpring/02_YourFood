package beforespring.yourfood.restaurant.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "member",
    indexes = {
        @Index(
            name = "idx__member__username__lunch_noti_status",
            columnList = "username, lunch_noti_status",
            unique = true
        )
    }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private Long password;

    @Column(precision = 3, scale = 6)
    private BigDecimal lat;

    @Column(precision = 3, scale = 6)
    private BigDecimal lon;

    @Enumerated(EnumType.STRING)
    @Column(name = "lunch_noti_status")
    private LunchNotiStatus lunchNotiStatus;

    @Column(name = "noti_agreed_at")
    private LocalDateTime noitAgreedAt;

    @Builder
    public Member(
        String username,
        String email,
        Long password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.lunchNotiStatus = LunchNotiStatus.DISAGREE;
    }

    /**
     * 회원의 위도와 경도 업데이트
     *
     * @param lat 위도
     * @param lon 경도
     */
    public void updateMemberLocation(BigDecimal lat, BigDecimal lon) {
        this.lat = lat;
        this.lon = lon;
    }

    /**
     * 회원의 점심 추천 동의 상태 업데이트
     *
     * @param status 동의 상태
     * @param date   동의 날짜
     */
    public void updateLunchNotiStatus(LunchNotiStatus status, LocalDateTime date) {
        this.lunchNotiStatus = status;
        this.noitAgreedAt = date;
    }
}
