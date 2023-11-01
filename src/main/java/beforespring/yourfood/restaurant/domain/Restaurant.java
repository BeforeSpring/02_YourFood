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

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean operating;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean deleted;

    @Builder
    public Restaurant(
        String name,
        String description,
        String address,
        Coordinate coordinate,
        CuisineType cuisineType,
        Boolean operating) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.coordinate = coordinate;
        this.cuisineType = cuisineType;
        this.rating = 0.0;
        this.operating = operating;
        this.deleted = false;
    }

    /**
     * 레스토랑 정보 업데이트
     *
     * @param description 수정할 식당 설명
     * @param address     수정할 식당 주소
     * @param coordinate  수정할 좌표
     * @param operating   운영 여부
     */
    public void updateRestaurant(
        String description,
        String address,
        AddressCode addressCode,
        Coordinate coordinate,
        Boolean operating) {
        this.description = description;
        this.address = address;
        this.addressCode = addressCode;
        this.coordinate = coordinate;
        this.operating = operating;
    }

    /**
     * 레스토랑 db를 삭제 상태로 변경
     */
    public void deleteRestaurantDb() {
        this.deleted = true;
    }
}
