package beforespring.yourfood.app.review.service.event;

import beforespring.yourfood.app.restaurant.domain.Restaurant;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Validated
public class UpdateReviewEvent {
    private final Restaurant restaurant;
    private final BigDecimal rating;
    private final BigDecimal beforeRating;

    public UpdateReviewEvent(@NotBlank Restaurant restaurant, @NotBlank Integer rating, @NotBlank Integer beforeRating) {
        this.restaurant = restaurant;
        this.rating = BigDecimal.valueOf(rating).setScale(5, RoundingMode.HALF_UP);
        this.beforeRating = BigDecimal.valueOf(beforeRating).setScale(5, RoundingMode.HALF_UP);
    }
}
