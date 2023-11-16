package beforespring.yourfood.app.review.infra;

import beforespring.yourfood.app.review.domain.Review;
import beforespring.yourfood.app.review.domain.ReviewQueryRepository;
import beforespring.yourfood.app.utils.OrderBy;
import beforespring.yourfood.app.review.exception.InvalidOrderByException;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static beforespring.yourfood.app.review.domain.QReview.review;

@Repository
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository {
    private final EntityManager em;


    public ReviewQueryRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Review> findReviewsByRestaurantIdOrderBy(boolean desc, OrderBy orderBy, Long restaurantId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        JPAQuery<Review> query = queryFactory.selectFrom(review)
            .where(review.restaurant.id.eq(restaurantId));

        switch (orderBy) {
            case RATING -> query.orderBy(desc ? review.rating.desc() : review.rating.asc());
            case TIME -> query.orderBy(desc ? review.createdAt.desc() : review.createdAt.asc());
            default -> throw new InvalidOrderByException(orderBy.name());
        }

        return query.fetch();
    }
}
