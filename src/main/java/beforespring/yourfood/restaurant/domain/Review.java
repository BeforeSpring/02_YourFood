package beforespring.yourfood.restaurant.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {
    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "restaurant_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    @Column(columnDefinition = "varchar(1000)")
    private String content;

    @Column(columnDefinition = "min(1), max(5)")
    private Long rating;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @Builder
    public Review(
        Member member,
        Restaurant restaurant,
        String content,
        Long rating,
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
    public void updateReview(String content, Long rating, LocalDateTime updatedAt) {
        this.content = content;
        this.rating = rating;
        this.updatedAt = updatedAt;
    }

}
