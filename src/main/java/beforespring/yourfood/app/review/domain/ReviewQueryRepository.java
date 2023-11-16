package beforespring.yourfood.app.review.domain;




import beforespring.yourfood.app.utils.OrderBy;

import java.util.List;

public interface ReviewQueryRepository {
    /**
     * 정렬 기준에 따른 레스토랑 ID에 대한 리뷰 목록
     *
     * @param desc         내림차순 여부
     * @param orderBy      정렬 기준 (RATING, TIME)
     * @param restaurantId 리뷰를 가져올 레스토랑의 ID
     * @return 정렬된 리뷰 목록
     */
    List<Review> findReviewsByRestaurantIdOrderBy(boolean desc, OrderBy orderBy, Long restaurantId);
}
