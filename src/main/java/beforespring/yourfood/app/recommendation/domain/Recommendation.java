package beforespring.yourfood.app.recommendation.domain;


import beforespring.yourfood.app.restaurant.service.dto.CuisineGroup;

import java.util.List;

/**
 * 추천 데이터. 구현 필요.
 */
public record Recommendation(
        Subscriber subscriber,
        List<CuisineGroup> recommendation
) {
}
