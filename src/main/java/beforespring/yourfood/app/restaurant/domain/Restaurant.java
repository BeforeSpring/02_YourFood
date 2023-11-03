package beforespring.yourfood.app.restaurant.domain;

import beforespring.yourfood.app.utils.Coordinate;
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
            columnList = "name, address",
            unique = true
        ),
        @Index(
            name = "idx__restaurant__name",
            columnList = "name"
        ),
        @Index(
            name = "idx__restaurant__sido__sigungu",
            columnList = "sido, sigungu"
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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String address;

    @Embedded
    private AddressCode addressCode;

    @Embedded
    private Coordinate coordinate;

    @Enumerated(EnumType.STRING)
    @Column(name = "cuisine_type", nullable = false)
    private CuisineType cuisineType;

    @Column(nullable = false, precision = 7, scale = 5, columnDefinition = "DECIMAL(7,5)")
    private BigDecimal rating;

    @Column(nullable = false)
    private boolean operating;

    @Column(nullable = false)
    private boolean deleted;

    @Builder
    public Restaurant(
        String name,
        String description,
        String address,
        AddressCode addressCode,
        Coordinate coordinate,
        CuisineType cuisineType,
        Boolean operating) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.addressCode = addressCode;
        this.coordinate = coordinate;
        this.cuisineType = cuisineType;
        this.rating = BigDecimal.valueOf(0.0);
        this.operating = operating;
        this.deleted = false;
    }

    /**
     * 식당 운영 상태 수정
     * @param operating 운영 중인지 여부
     */
    public void updateOperating(Boolean operating) {
        this.operating = operating;
    }

    /**
     * 식당 설명 수정
     * @param description 식당 설명
     */
    public void updateDescription(String description) {
        this.description = description;
    }

    /**
     * 레스토랑 db를 삭제 상태로 변경
     */
    public void deleteRestaurantDb() {
        this.deleted = true;
    }

    /**
     * 식당 평점 수정
     * @param rating 평점
     */
    public void updateRating(BigDecimal rating) {
        this.rating = rating;
    }
}