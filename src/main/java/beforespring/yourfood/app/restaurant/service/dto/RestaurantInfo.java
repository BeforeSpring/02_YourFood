package beforespring.yourfood.app.restaurant.service.dto;
import beforespring.yourfood.app.restaurant.domain.Restaurant;
import lombok.*;

import java.math.BigDecimal;

public record RestaurantInfo(String name, BigDecimal rating, String description, String address) {
    @Builder
    public RestaurantInfo {
    }
    public static RestaurantInfo createFromRestaurant(Restaurant restaurant) {
        return new RestaurantInfo(
            restaurant.getName(),
            restaurant.getRating(),
            restaurant.getDescription(),
            restaurant.getAddress()
        );
    }
}
