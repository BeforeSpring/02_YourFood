package beforespring.yourfood.app.restaurant.service;

import beforespring.yourfood.app.restaurant.domain.Restaurant;
import beforespring.yourfood.app.restaurant.domain.RestaurantRepository;
import beforespring.yourfood.app.restaurant.exception.RestaurantNotFoundException;
import beforespring.yourfood.app.restaurant.service.dto.RestaurantWithReviewDto;
import beforespring.yourfood.app.review.domain.Review;
import beforespring.yourfood.app.restaurant.service.dto.ReviewDto;
import beforespring.yourfood.app.review.domain.ReviewRepository;
import beforespring.yourfood.app.utils.Coordinates;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static beforespring.yourfood.app.utils.RestaurantComparators.byDistance;
import static beforespring.yourfood.app.utils.RestaurantComparators.byRatingAverage;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;

    public RestaurantWithReviewDto getRestaurantDetail(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(RestaurantNotFoundException::new);
        List<Review> reviews = reviewRepository.findByRestaurantId(restaurantId);
        List<ReviewDto> reviewDtos = ReviewDto.mapReviewsToReviewDtos(reviews);

        return RestaurantWithReviewDto.createFrom(restaurant, reviewDtos);
    }

    @Override
    public List<Restaurant> getRestaurantsByRating(boolean descendingOrder) {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        Comparator<Restaurant> ratingComparator = byRatingAverage(descendingOrder);

        // 평점순으로 정렬
        restaurants.sort(ratingComparator);
        return restaurants;
    }

    @Override
    public List<Restaurant> getRestaurantsByDistance(boolean descendingOrder, Coordinates currentCoords) {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        Comparator<Restaurant> distanceComparator = byDistance(descendingOrder, currentCoords);

        // 거리순으로 정렬
        restaurants.sort(distanceComparator);
        return restaurants;
    }
}

