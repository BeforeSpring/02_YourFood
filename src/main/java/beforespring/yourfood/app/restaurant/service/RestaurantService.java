package beforespring.yourfood.app.restaurant.service;

import beforespring.yourfood.app.restaurant.domain.Restaurant;
import beforespring.yourfood.app.restaurant.service.dto.RestaurantWithReviewDto;
import beforespring.yourfood.app.review.service.event.CreateReviewEvent;
import beforespring.yourfood.app.review.service.event.UpdateReviewEvent;
import beforespring.yourfood.app.utils.Coordinates;
import beforespring.yourfood.app.utils.OrderBy;
import beforespring.yourfood.web.api.restaurant.response.RestaurantDto;

import java.util.List;

public interface RestaurantService {
    /**
     * 레스토랑 상세 정보 조회
     *
     * @param restaurantId 레스토랑 id
     * @return 레스토랑 및 리뷰 정보
     */
    RestaurantWithReviewDto getRestaurantDetail(Long restaurantId);

    /**
     * 새로운 리뷰 평점을 식당 평점에 업데이트함
     *
     * @param event 리뷰 생성 이벤트
     */
    void updateNewReviewRating(CreateReviewEvent event);

    /**
     * 수정된 리뷰의 평점을 식당 평점에 업데이트함
     *
     * @param event 리뷰 수정 이벤트
     */
    void updateModifiedReviewRating(UpdateReviewEvent event);

    /**
     * 평점순으로 레스토랑 목록 조회
     *
     * @param descendingOrder 정렬 여부
     * @param coordinates     좌표
     * @param rangeInMeter    반경
     * @return 평점순으로 정렬된 레스토랑 목록
     */
    List<Restaurant> getRestaurantsByRating(boolean descendingOrder, Coordinates coordinates, int rangeInMeter);

    /**
     * 거리순으로 레스토랑 목록 조회
     *
     * @param descendingOrder 정렬 여부
     * @param coordinates     좌표
     * @param rangeInMeter    반경
     * @return 거리순으로 정렬된 레스토랑 목록
     */
    List<Restaurant> getRestaurantsByDistance(boolean descendingOrder, Coordinates coordinates, int rangeInMeter);

    List<RestaurantDto> getRestaurants(OrderBy orderBy, boolean descendingOrder, Coordinates coordinates, int rangeInMeter);
}
