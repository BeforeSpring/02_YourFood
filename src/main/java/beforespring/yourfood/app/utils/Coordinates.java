package beforespring.yourfood.app.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Coordinates {
    @Column(nullable = false, precision = 11, scale = 8, columnDefinition = "DECIMAL(11,8)")
    private BigDecimal lat;
    @Column(nullable = false, precision = 11, scale = 8, columnDefinition = "DECIMAL(11,8)")
    private BigDecimal lon;

    public BigDecimal calculateDistance(Coordinates destination) {
        return Coordinates.calculateDistance(this, destination);
    }

    public static BigDecimal calculateDistance(Coordinates origin, Coordinates destination) {
        BigDecimal xDiff = destination.getLon().subtract(origin.getLon());
        BigDecimal yDiff = destination.getLat().subtract(origin.getLat());

        BigDecimal xDiffSquared = xDiff.pow(2);
        BigDecimal yDiffSquared = yDiff.pow(2);

        MathContext mc = new MathContext(11);
        BigDecimal distance = xDiffSquared.add(yDiffSquared).sqrt(mc); // 루트 계산

        return distance.setScale(8, RoundingMode.HALF_UP);
    }

    public void setCoordinates(BigDecimal lat, BigDecimal lon) {
        this.lat = lat;
        this.lon = lon;
    }
}
