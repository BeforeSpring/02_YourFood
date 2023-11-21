package beforespring.yourfood.app.review.service.event;

import beforespring.yourfood.app.restaurant.domain.Restaurant;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Validated
public class CreateReviewEvent {
    private final Restaurant restaurant;
    private final BigDecimal rating;

    public CreateReviewEvent(@NotBlank Restaurant restaurant, @NotBlank Integer rating) {
        this.restaurant = restaurant;
        this.rating = BigDecimal.valueOf(rating).setScale(5, RoundingMode.HALF_UP);
    }
}
