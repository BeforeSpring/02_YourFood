package beforespring.yourfood.web.api.restaurant.response;

import beforespring.yourfood.app.restaurant.domain.CuisineType;
import beforespring.yourfood.app.restaurant.domain.Restaurant;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record RestaurantListResponse(Long id,
                                     String name,
                                     String description,
                                     String address,
                                     Set<CuisineType> cuisineType,
                                     BigDecimal rating) {
    public static List<RestaurantListResponse> mapToRestaurantListResponse(List<Restaurant> restaurants) {
        return restaurants.stream()
            .map(restaurant -> new RestaurantListResponse(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getDescription(),
                restaurant.getAddress(),
                restaurant.getCuisineType(),
                restaurant.getRating()
            ))
            .collect(Collectors.toList());
    }
}

