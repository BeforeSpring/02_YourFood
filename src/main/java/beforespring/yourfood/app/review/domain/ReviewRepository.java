package beforespring.yourfood.app.review.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r WHERE r.restaurant.id = :restaurantId")
    List<Review> findByRestaurantId(@Param("restaurantId") Long restaurantId);

    @Query("SELECT r FROM Review r WHERE r.restaurant.id = :restaurantId")
    Page<Review> findReviewsByRestaurantIdPaged(@Param("restaurantId") Long restaurantId, Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.restaurant.id = :restaurantId AND r.updatedAt < :restaurantRatingUpdatedAt AND r.reflectedRating = false")
    List<Review> findUnreflectedRatingModifiedReviews(@Param("restaurantId") Long restaurantId, @Param("restaurantRatingUpdatedAt") LocalDateTime restaurantRatingUpdatedAt);

    @Query("SELECT r FROM Review r WHERE r.restaurant.id = :restaurantId AND r.updatedAt IS NULL AND r.reflectedRating = false")
    List<Review> findUnreflectedRatingNewReviews(@Param("restaurantId") Long restaurantId);
}
