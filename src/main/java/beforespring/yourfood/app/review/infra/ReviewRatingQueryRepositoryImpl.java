package beforespring.yourfood.app.review.infra;

import beforespring.yourfood.app.review.domain.Review;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static beforespring.yourfood.app.review.domain.QReview.review;

@Repository
public class ReviewRatingQueryRepositoryImpl {
    private final JPAQueryFactory jpaQueryFactory;

    public ReviewRatingQueryRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    public List<Review> findUnreflectedModifiedReview(Long restaurantId, LocalDateTime restaurantRatingUpdatedAt) {
        return jpaQueryFactory
                   .selectFrom(review)
                   .where(
                       review.restaurantId.eq(restaurantId),
                       review.updatedAt.loe(restaurantRatingUpdatedAt)
                   )
                   .stream()
                   .toList();
    }

    public List<Review> findUnreflectedNewReview(Long restaurantId, LocalDateTime restaurantRatingUpdatedAt) {
        return jpaQueryFactory
                   .selectFrom(review)
                   .where(
                       review.restaurantId.eq(restaurantId),
                       review.updatedAt.isNull(),
                       review.createdAt.loe(restaurantRatingUpdatedAt)
                   )
                   .stream()
                   .toList();
    }
}
