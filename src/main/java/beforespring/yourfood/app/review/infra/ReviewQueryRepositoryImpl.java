package beforespring.yourfood.app.review.infra;

import beforespring.yourfood.app.review.domain.Review;
import beforespring.yourfood.app.review.domain.ReviewQueryRepository;
import beforespring.yourfood.app.utils.OrderBy;
import beforespring.yourfood.app.review.exception.InvalidOrderByException;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static beforespring.yourfood.app.review.domain.QReview.review;

@Repository
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;


    public ReviewQueryRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Review> findReviewsByRestaurantIdOrderBy(boolean desc, OrderBy orderBy, Long restaurantId, Pageable pageable) {
        JPAQuery<Review> query = jpaQueryFactory.selectFrom(review)
            .where(review.restaurant.id.eq(restaurantId));

        if (orderBy != null) {
            query.orderBy(getOrderSpecifier(orderBy, desc));
        }

        List<Review> reviews = query
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        return new PageImpl<>(reviews, pageable, reviews.size());
    }

    private OrderSpecifier<?> getOrderSpecifier(OrderBy orderBy, boolean desc) {
        NumberPath<Integer> rating = review.rating;
        DateTimePath<LocalDateTime> createdAt = review.createdAt;

        switch (orderBy) {
            case RATING -> {
                return desc ? rating.desc() : rating.asc();
            }
            case TIME -> {
                return desc ? createdAt.desc() : createdAt.asc();
            }
            default -> throw new InvalidOrderByException(orderBy.name());
        }
    }
}
