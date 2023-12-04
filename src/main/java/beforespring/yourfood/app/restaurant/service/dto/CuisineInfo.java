package beforespring.yourfood.app.restaurant.service.dto;

import beforespring.yourfood.app.restaurant.domain.CuisineType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CuisineInfo {
    private Set<CuisineType> cuisineType;
    private List<RestaurantInfo> restaurants;

    public CuisineInfo(Set<CuisineType> cuisineType, List<RestaurantInfo> restaurants) {
        this.cuisineType = cuisineType;
        this.restaurants = restaurants;
    }
}
