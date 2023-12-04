package beforespring.yourfood.app.restaurant.service.dto;
import lombok.*;

import java.math.BigDecimal;

@Getter
public class RestaurantInfo {
    private String name;
    private BigDecimal rating;
    private String description;
    private String address;

    @Builder
    public RestaurantInfo(String name, BigDecimal rating, String description, String address) {
        this.name = name;
        this.rating = rating;
        this.description = description;
        this.address = address;
    }
}
