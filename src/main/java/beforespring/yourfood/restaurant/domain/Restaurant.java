package beforespring.yourfood.restaurant.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(
    name = "restaurant",
    indexes = {
        @Index(
            name = "idx__restaurant__name__address",
            columnList = "name_address",
            unique = true
        )
    }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Restaurant {
    @Id
    @Column(name = "restaurant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_address")
    private String nameAddress;

    private String name;

    private String description;

    private String address;

    @Column(precision = 3, scale = 6)
    private BigDecimal lat;

    @Column(precision = 3, scale = 6)
    private BigDecimal lon;

    @Enumerated(EnumType.STRING)
    @Column(name = "cuisine_type")
    private CuisineType cuisineType;

    private Double rating;

    @Builder
    public Restaurant(
        String name,
        String description,
        String address,
        BigDecimal lat,
        BigDecimal lon,
        CuisineType cuisineType) {
        this.nameAddress = name + address;
        this.name = name;
        this.description = description;
        this.address = address;
        this.lat = lat;
        this.lon = lon;
        this.cuisineType = cuisineType;
        this.rating = 0.0;
    }

    /**
     * 레스토랑 정보 업데이트
     *
     * @param description 수정할 식당 설명
     * @param address     수정할 식당 주소
     * @param lat         수정할 위도
     * @param lon         수정할 경도
     */
    public void updateRestaurant(
        String description,
        String address,
        BigDecimal lat,
        BigDecimal lon) {
        this.description = description;
        this.nameAddress = this.name + address;
        this.address = address;
        this.lat = lat;
        this.lon = lon;
    }
}
