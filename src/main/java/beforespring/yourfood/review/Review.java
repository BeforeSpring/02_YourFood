package beforespring.yourfood.review;

import beforespring.yourfood.member.domain.Member;
import beforespring.yourfood.restaurant.domain.Restaurant;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Review {
    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "restaurant_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    @Column(nullable = false)
    private String content;

    @Column(columnDefinition = "TINYINT", nullable = false)
    private Byte rating;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @Builder
    public Review(
        Member member,
        Restaurant restaurant,
        String content,
        Byte rating,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
        this.member = member;
        this.restaurant = restaurant;
        this.content = content;
        this.rating = rating;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * 리뷰 업데이트
     *
     * @param content   수정할 내용
     * @param rating    수정할 평점
     * @param updatedAt 수정 시간
     */
    public void updateReview(String content, Byte rating, LocalDateTime updatedAt) {
        this.content = content;
        this.rating = rating;
        this.updatedAt = updatedAt;
    }

}
