package beforespring.yourfood.utils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class Coordinate {
    @Column(nullable = false, precision = 3, scale = 8, columnDefinition = "DECIMAL(3,8)")
    private BigDecimal lat;
    @Column(nullable = false ,precision = 3, scale = 8, columnDefinition = "DECIMAL(3,8)")
    private BigDecimal lon;
}
