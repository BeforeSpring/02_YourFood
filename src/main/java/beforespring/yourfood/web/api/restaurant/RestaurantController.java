package beforespring.yourfood.web.api.restaurant;

<<<<<<< HEAD
import beforespring.yourfood.web.api.common.GenericResponse;
=======
>>>>>>> de6ed1085a77161aa78396c58656975e7e244a4b
import beforespring.yourfood.web.api.restaurant.response.RegionListResponse;
import beforespring.yourfood.web.api.restaurant.response.RestaurantDetailResponse;
import beforespring.yourfood.web.api.restaurant.response.RestaurantListResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {
    /**
     * 모든 시군구 목록 조회
     * @return
     */
    @GetMapping("/regions")
<<<<<<< HEAD
    public GenericResponse<RegionListResponse> getRegions() {
        return null;
=======
    public RegionListResponse getRegions() {
        return new RegionListResponse();
>>>>>>> de6ed1085a77161aa78396c58656975e7e244a4b
    }

    /**
     * 맛집 상세 정보 조회
     * @param restaurant_id 맛집 id
     * @return
     */

    @GetMapping("/{restaurant_id}")
<<<<<<< HEAD
    public GenericResponse<RestaurantDetailResponse> getRestaurantDetail(@PathVariable Long restaurant_id) {
        return null;
=======
    public RestaurantDetailResponse getRestaurantDetail(@PathVariable Long restaurant_id) {
        return new RestaurantDetailResponse();
>>>>>>> de6ed1085a77161aa78396c58656975e7e244a4b
    }

    /**
     * 지역별 맛집 목록 조회
     * @param region 조회할 지역명
     * @param range 조회 반경 거리
     * @param orderBy 정렬 기준
     * @return
     */
    @GetMapping
<<<<<<< HEAD
    public GenericResponse<RestaurantListResponse> getRestaurantsByRegion(@RequestParam String region,
                                                         @RequestParam int range,
                                                         @RequestParam(required = false) String orderBy) {
        return null;
=======
    public RestaurantListResponse getRestaurantsByRegion(@RequestParam String region,
                                                         @RequestParam int range,
                                                         @RequestParam(required = false) String orderBy) {
        return new RestaurantListResponse();
>>>>>>> de6ed1085a77161aa78396c58656975e7e244a4b
    }

    /**
     * 내 주변 맛집 목록 조회
     * @param range 조회 반경 거리
     * @param lat 위도
     * @param lon 경도
     * @param orderBy 정렬 기준
     * @return
     */
    @GetMapping("/nearby")
<<<<<<< HEAD
    public GenericResponse<RestaurantListResponse> getNearbyRestaurants(@RequestParam int range,
=======
    public RestaurantListResponse getNearbyRestaurants(@RequestParam int range,
>>>>>>> de6ed1085a77161aa78396c58656975e7e244a4b
                                                       @RequestParam String lat,
                                                       @RequestParam String lon,
                                                       @RequestParam(required = false) String orderBy) {

<<<<<<< HEAD
        return null;
=======
        return new RestaurantListResponse();
>>>>>>> de6ed1085a77161aa78396c58656975e7e244a4b
    }

}
