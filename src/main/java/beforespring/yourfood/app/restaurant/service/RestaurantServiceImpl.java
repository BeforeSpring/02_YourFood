package beforespring.yourfood.app.restaurant.service;

import beforespring.yourfood.app.restaurant.domain.CuisineType;
import beforespring.yourfood.app.restaurant.domain.Restaurant;
import beforespring.yourfood.app.restaurant.domain.RestaurantQueryRepository;
import beforespring.yourfood.app.restaurant.domain.RestaurantRepository;
import beforespring.yourfood.app.restaurant.exception.RestaurantNotFoundException;
import beforespring.yourfood.app.restaurant.service.dto.CuisineInfo;
import beforespring.yourfood.app.restaurant.service.dto.RestaurantInfo;
import beforespring.yourfood.app.restaurant.service.dto.RestaurantWithReviewDto;
import beforespring.yourfood.app.restaurant.service.dto.ReviewDto;
import beforespring.yourfood.app.review.domain.Review;
import beforespring.yourfood.app.review.domain.ReviewRepository;
import beforespring.yourfood.app.utils.Coordinates;
import beforespring.yourfood.app.utils.OrderBy;
import beforespring.yourfood.web.api.restaurant.response.RestaurantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static beforespring.yourfood.app.utils.RestaurantComparators.byDistance;
import static beforespring.yourfood.app.utils.RestaurantComparators.byRatingAverage;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;
    private final RestaurantQueryRepository restaurantQueryRepository;

    @Override
    @Transactional(readOnly = true)
    public RestaurantWithReviewDto getRestaurantDetail(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(RestaurantNotFoundException::new);
        List<Review> reviews = reviewRepository.findByRestaurantId(restaurantId);
        List<ReviewDto> reviewDtos = ReviewDto.mapReviewsToReviewDtos(reviews);

        return RestaurantWithReviewDto.createFrom(restaurant, reviewDtos);
    }

    @Override
    public List<Restaurant> getRestaurantsByRating(boolean descendingOrder, Coordinates coordinates, int rangeInMeter) {
        List<Restaurant> restaurantsInLocation = restaurantQueryRepository.findAllWithin(coordinates, rangeInMeter);
        // 평점순 정렬
        restaurantsInLocation.sort(byRatingAverage(descendingOrder));
        return restaurantsInLocation;
    }

    @Override
    public List<Restaurant> getRestaurantsByDistance(boolean descendingOrder, Coordinates coordinates, int rangeInMeter) {
        List<Restaurant> restaurantsInLocation = restaurantQueryRepository.findAllWithin(coordinates, rangeInMeter);
        // 거리순 정렬
        restaurantsInLocation.sort(byDistance(descendingOrder, coordinates));
        return restaurantsInLocation;
    }

    @Override
    public List<RestaurantDto> getRestaurants(OrderBy orderBy, boolean descendingOrder, Coordinates coordinates, int rangeInMeter) {
        List<Restaurant> restaurantsInLocation = new ArrayList<>(restaurantQueryRepository.findAllWithin(coordinates, rangeInMeter));

        restaurantsInLocation.sort(
            (orderBy == OrderBy.RATING) ?
                byRatingAverage(descendingOrder) :
                byDistance(descendingOrder, coordinates)
        );

        return restaurantsInLocation.stream()
                   .map(restaurant -> new RestaurantDto(
                       restaurant.getId(),
                       restaurant.getName(),
                       restaurant.getDescription(),
                       restaurant.getAddress(),
                       restaurant.getCuisineType(),
                       restaurant.getRating()
                   ))
                   .collect(Collectors.toList());
    }

    @Override
    public List<CuisineInfo> findRestaurantsByCuisineType(Coordinates currentCoords, int rangeInMeters) {
        List<Restaurant> allWithin = restaurantQueryRepository.findAllWithin(currentCoords, rangeInMeters);

        Map<CuisineType, List<Restaurant>> restaurantsByCuisineType = new HashMap<>();

        allWithin.forEach(restaurant -> restaurant.getCuisineType().forEach(cuisineType -> restaurantsByCuisineType
            .computeIfAbsent(cuisineType, k -> new ArrayList<>())
            .add(restaurant)));

        return restaurantsByCuisineType.entrySet().stream()
            .map(entry -> {
                CuisineType cuisineType = entry.getKey();
                List<RestaurantInfo> restaurantInfos = entry.getValue().stream()
                    .sorted(Comparator.comparing(Restaurant::getRating).reversed())
                    .limit(5)
                    .map(restaurant -> RestaurantInfo.builder()
                        .name(restaurant.getName())
                        .rating(restaurant.getRating())
                        .description(restaurant.getDescription())
                        .address(restaurant.getAddress())
                        .build())
                    .collect(Collectors.toList());

                return new CuisineInfo(Collections.singleton(cuisineType), restaurantInfos);
            })
            .collect(Collectors.toList());
    }
}

