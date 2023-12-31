package beforespring.yourfood.app.restaurant.service.dto;

import beforespring.yourfood.app.restaurant.domain.AddressCode;
import beforespring.yourfood.app.restaurant.domain.CuisineType;
import beforespring.yourfood.app.restaurant.domain.Restaurant;
import beforespring.yourfood.app.utils.Coordinates;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record RestaurantWithReviewDto(String name,
                                      String address,
                                      String description,
                                      AddressCode addressCode,
                                      Coordinates coordinate,
                                      Set<CuisineType> cuisineType,
                                      BigDecimal rating,
                                      boolean operating,
                                      boolean deleted,
                                      List<ReviewDto> reviews) {
    @Builder
    public RestaurantWithReviewDto {
    }

    public static RestaurantWithReviewDto createFrom(
        Restaurant restaurant, List<ReviewDto> reviewDtos) {
        return new RestaurantWithReviewDto(
            restaurant.getName(),
            restaurant.getAddress(),
            restaurant.getDescription(),
            restaurant.getAddressCode(),
            restaurant.getCoordinates(),
            restaurant.getCuisineType(),
            restaurant.getRating(),
            restaurant.isOperating(),
            restaurant.isDeleted(),
            reviewDtos
        );
    }
}
