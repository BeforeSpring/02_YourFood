package beforespring.yourfood.app.review.domain;

import beforespring.yourfood.app.member.domain.Member;
import beforespring.yourfood.app.restaurant.domain.Restaurant;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static beforespring.Fixture.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;
    @PersistenceContext
    private EntityManager em;

    @Test
    @DisplayName("수정된 리뷰 중 레스토랑에 반영되지 않은 리뷰를 찾음")
    void find_unreflected_rating_modified_reviews() {
        //given
        initNewReviewDummy(5);
        initModifiedReviewDummy(10);

        //when
        List<Review> findReviews = reviewRepository.findUnreflectedRatingModifiedReviews(LocalDateTime.now());

        //then
        Assertions.assertThat(findReviews)
            .describedAs("수정된 리뷰 중 레스토랑에 반영되지 않은 리뷰는 10개 입니다.")
            .hasSize(10);

    }

    @Test
    @DisplayName("반영되지 않은 새로운 리뷰를 찾음")
    void find_unreflected_rating_new_reviews() {
        //given
        initNewReviewDummy(5);
        initModifiedReviewDummy(10);

        //when
        List<Review> findReviews = reviewRepository.findUnreflectedRatingNewReviews();

        //then
        Assertions.assertThat(findReviews)
            .describedAs("새로운 리뷰 중 레스토랑에 반영되지 않은 리뷰는 5개 입니다.")
            .hasSize(5);
    }

    public void initModifiedReviewDummy(int howManySet) {
        Member member = aMember().build();
        Restaurant restaurant = aRestaurant().build();
        em.persist(member);
        em.persist(restaurant);
        List<Review> modifiedReviews = Stream.generate(() -> aReview()
                                                                 .member(member)
                                                                 .restaurant(restaurant)
                                                                 .build())
                                           .limit(howManySet)
                                           .toList();
        modifiedReviews.forEach(review -> {
            review.isReflectedRating(false);
            review.updateReview(randString(), random.nextInt(0, 6));
        });
        modifiedReviews.forEach(em::persist);
        em.flush();
        em.clear();
    }

    public void initNewReviewDummy(int howManySet) {
        Member member = aMember().build();
        Restaurant restaurant = aRestaurant().build();
        em.persist(member);
        em.persist(restaurant);
        List<Review> newReviews = Stream.generate(() -> aReview()
                                                            .member(member)
                                                            .restaurant(restaurant)
                                                            .build())
                                      .limit(howManySet)
                                      .toList();
        newReviews.forEach(review -> {
            review.isReflectedRating(false);
        });
        newReviews.forEach(em::persist);
        em.flush();
        em.clear();
    }
}