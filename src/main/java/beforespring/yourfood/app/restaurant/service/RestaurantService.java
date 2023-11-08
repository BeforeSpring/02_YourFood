package beforespring.yourfood.app.restaurant.service;

import beforespring.yourfood.app.restaurant.service.dto.RestaurantWithReviewDto;

public interface RestaurantService {
    RestaurantWithReviewDto getRestaurantDetail(Long restaurantId);

    /**
     * 새로운 리뷰의 평점을 식당 평점에 업데이트함
     *
     * @param restaurantId 업데이트 할 레스토랑 id
     * @param reviewId     수정된 리뷰 id
     */
    public boolean updateNewReviewRating(Long restaurantId, Long reviewId);

    /**
     * 수정된 리뷰의 평점을 식당 평점에 업데이트함
     *
     * @param restaurantId 업데이트 할 레스토랑 id
     * @param reviewId     수정된 리뷰 id
     */
    public boolean updateReviewRating(Long restaurantId, Long reviewId);
}
