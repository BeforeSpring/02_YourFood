package beforespring.yourfood.app.restaurant.service;

import beforespring.yourfood.app.exception.RestaurantNotFoundException;
import beforespring.yourfood.app.restaurant.domain.Restaurant;
import beforespring.yourfood.app.restaurant.domain.RestaurantRepository;
import beforespring.yourfood.app.review.domain.Review;
import beforespring.yourfood.app.review.infra.ReviewRatingQueryRepositoryImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RestaurantRatingService {
    RestaurantRepository restaurantRepository;
    ReviewRatingQueryRepositoryImpl reviewRepository;

    public void updateRatings(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(RestaurantNotFoundException::new);
        LocalDateTime lastRatingUpdatedAt = restaurant.getRatingUpdatedAt();
        List<Review> unreflectedNewReviews = reviewRepository.findUnreflectedNewReview(restaurantId, lastRatingUpdatedAt);
        List<Review> unreflectedModifiedReviews = reviewRepository.findUnreflectedModifiedReview(restaurantId, lastRatingUpdatedAt);

        for (Review newReview : unreflectedNewReviews) {
            restaurant.updateReviewRating(
                BigDecimal.valueOf(newReview.getRating()),
                BigDecimal.ZERO,
                1);
        }
        for (Review modifiedReview : unreflectedModifiedReviews) {
            restaurant.updateReviewRating(
                BigDecimal.valueOf(modifiedReview.getRating()),
                BigDecimal.valueOf(modifiedReview.getBeforeRating()),
                0
            );
        }
    }
}
