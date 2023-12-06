package beforespring.yourfood.app.recommendation.infra;

import beforespring.yourfood.app.recommendation.domain.Recommendation;
import beforespring.yourfood.app.recommendation.domain.Recommender;
import beforespring.yourfood.app.recommendation.domain.Subscriber;
import beforespring.yourfood.app.restaurant.service.RestaurantService;
import beforespring.yourfood.app.restaurant.service.dto.CuisineGroup;
import beforespring.yourfood.app.utils.OrderBy;
import java.util.List;

/**
 * 빈 주입을 위해 임시로 만들어둔 클래스.
 */
public class RecommenderImpl implements Recommender {
    private final RestaurantService restaurantService;
    private final int rangeInMeters;
    private final int limitByCuisineType;

    public RecommenderImpl(RestaurantService restaurantService, int rangeInMeters, int limitByCuisineType) {
        this.restaurantService = restaurantService;
        this.rangeInMeters = rangeInMeters;
        this.limitByCuisineType = limitByCuisineType;
    }

    @Override
    public Recommendation getRecommendation(Subscriber subscriber) {
        List<CuisineGroup> cuisineGroups = restaurantService.getRestaurantGroupedBy(OrderBy.RATING, true, subscriber.coordinates(), rangeInMeters, limitByCuisineType);
        return new Recommendation(subscriber, cuisineGroups);
    }
}
