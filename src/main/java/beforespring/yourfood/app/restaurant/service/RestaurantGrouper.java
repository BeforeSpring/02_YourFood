package beforespring.yourfood.app.restaurant.service;

import beforespring.yourfood.app.restaurant.domain.CuisineType;
import beforespring.yourfood.app.restaurant.service.dto.CuisineGroup;
import beforespring.yourfood.app.restaurant.service.dto.RestaurantDto;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class RestaurantGrouper {

    public List<CuisineGroup> groupByCuisineGroup(List<RestaurantDto> restaurants) {
        Map<CuisineType, List<RestaurantDto>> cuisineTypeListMap = groupRestaurantsByCuisineType(
            restaurants);
        return cuisineTypeListMap.entrySet()
                   .stream()
                   .map(this::createCuisineGroup)
                   .toList();
    }

//    record RestaurantTuple(
//        CuisineType cuisineType,
//        RestaurantDto restaurantDto
//    ) {
//
//    }


    /**
     * CuisineType 에 따라 맛집을 그룹화
     */
    private Map<CuisineType, List<RestaurantDto>> groupRestaurantsByCuisineType(
        List<RestaurantDto> restaurants
    ) {
//        Map<CuisineType, List<RestaurantTuple>> collect = restaurants.stream()
//                                                              .flatMap(
//                                                                  restaurantDto -> restaurantDto.cuisineType()
//                                                                                       .stream()
//                                                                                       .map(
//                                                                                           cuisineType -> new RestaurantTuple(
//                                                                                               cuisineType,
//                                                                                               restaurantDto))
//                                                              )
//                                                              .collect(Collectors.groupingBy(
//                                                                  RestaurantTuple::cuisineType)
//                                                              );

        Map<CuisineType, List<RestaurantDto>> restaurantsByCuisineType = new HashMap<>();

        for (RestaurantDto restaurant : restaurants) {
            for (CuisineType cuisineType : restaurant.cuisineType()) {
                restaurantsByCuisineType
                    .computeIfAbsent(
                        cuisineType,
                        k -> new ArrayList<>()
                    )
                    .add(restaurant);
            }
        }

        return restaurantsByCuisineType;
    }

    private CuisineGroup createCuisineGroup(Map.Entry<CuisineType, List<RestaurantDto>> entry) {
        CuisineType cuisineType = entry.getKey();
        List<RestaurantDto> restaurantInfos = entry.getValue().stream()
                                                  .sorted(
                                                      Comparator.comparing(RestaurantDto::rating)
                                                          .reversed())
                                                  .limit(5)
                                                  .collect(Collectors.toList());
        return new CuisineGroup(cuisineType, restaurantInfos);
    }

}
