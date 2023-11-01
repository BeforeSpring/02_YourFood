package beforespring.yourfood.restaurant.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
