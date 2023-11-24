package beforespring.yourfood.app.review.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r WHERE r.restaurantId = :restaurantId")
    List<Review> findByRestaurantId(@Param("restaurantId") Long restaurantId);
}
