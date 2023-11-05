package beforespring.yourfood.app.utils;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(
    name = "sgg_lat_lon",
    indexes = {
        @Index(
            name = "idx__si_do__si_gun_gu",
            columnList = "si_do, si_gun_gu"
        )
    }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SggLatLon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sgg_lat_lon_id")
    private Long id;
    @Column(name = "si_do")
    private String siDo;
    @Column(name = "si_gun_gu")
    private String siGunGu;
    private String lon;
    private String lat;

    @Builder
    public SggLatLon(String siDo, String siGunGu, String lon, String lat) {
        this.siDo = siDo;
        this.siGunGu = siGunGu;
        this.lon = lon;
        this.lat = lat;
    }
}
