package beforespring.yourfood.restaurant.domain;

import beforespring.yourfood.utils.Coordinate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column(nullable = false)
    private Double rating;

    @Builder
    public Restaurant(
        String name,
        String description,
        String address,
        Coordinate coordinate,
        CuisineType cuisineType) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.coordinate = coordinate;
        this.cuisineType = cuisineType;
        this.rating = 0.0;
    }

    /**
     * 레스토랑 정보 업데이트
     *
     * @param description 수정할 식당 설명
     * @param address     수정할 식당 주소
     * @param coordinate  수정할 좌표
     */
    public void updateRestaurant(
        String description,
        String address,
        AddressCode addressCode,
        Coordinate coordinate) {
        this.description = description;
        this.address = address;
        this.addressCode = addressCode;
        this.coordinate = coordinate;
    }
}
