package beforespring.yourfood.restaurant.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Menu {
    @Id
    @Column(name = "menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "restaurant_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    private String name;

    @Column(columnDefinition = "varchar(1000)")
    private String description;

    private String price;

    @Builder
    public Menu(
        Restaurant restaurant,
        String name,
        String description,
        String price) {
        this.restaurant = restaurant;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void updatePrice(String price) {
        this.price = price;
    }
}
