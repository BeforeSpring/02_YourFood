package beforespring.yourfood.batch.rawrestaurant.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name = "raw_restaurant",
    indexes = {
        @Index(
            name = "idx__raw_restaurant__bizplc_nm__refine_roadnm_addr",
            columnList = "bizplc_nm, refine_roadnm_addr",
            unique = true
        )
    }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RawRestaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String SIGUN_NM;
    private String SIGUN_CD;
    private String BIZPLC_NM;
    private String LICENSG_DE;
    private String BSN_STATE_NM;
    private String CLSBIZ_DE;
    private String LOCPLC_AR;
    private String GRAD_FACLT_DIV_NM;
    private String MALE_ENFLPSN_CNT;
    private String YY;
    private String MULTI_USE_BIZESTBL_YN;
    private String TOT_FACLT_SCALE;
    private String FEMALE_ENFLPSN_CNT;
    private String BSNSITE_CIRCUMFR_DIV_NM;
    private String SANITTN_INDUTYPE_NM;
    private String SANITTN_BIZCOND_NM;
    private String TOT_EMPLY_CNT;
    private String REFINE_ROADNM_ADDR;
    private String REFINE_LOTNO_ADDR;
    private String REFINE_ZIP_CD;
    @Column(precision = 11, scale = 8)
    private BigDecimal REFINE_WGS84_LAT;
    @Column(precision = 11, scale = 8)
    private BigDecimal REFINE_WGS84_LOGT;

    @Builder
    public RawRestaurant(
        Long id,
        String SIGUN_NM,
        String SIGUN_CD,
        String BIZPLC_NM,
        String LICENSG_DE,
        String BSN_STATE_NM,
        String CLSBIZ_DE,
        String LOCPLC_AR,
        String GRAD_FACLT_DIV_NM,
        String MALE_ENFLPSN_CNT,
        String YY,
        String MULTI_USE_BIZESTBL_YN,
        String TOT_FACLT_SCALE,
        String FEMALE_ENFLPSN_CNT,
        String BSNSITE_CIRCUMFR_DIV_NM,
        String SANITTN_INDUTYPE_NM,
        String SANITTN_BIZCOND_NM,
        String TOT_EMPLY_CNT,
        String REFINE_ROADNM_ADDR,
        String REFINE_LOTNO_ADDR,
        String REFINE_ZIP_CD,
        String REFINE_WGS84_LAT,
        String REFINE_WGS84_LOGT
    ) {
        this.id = id;
        this.SIGUN_NM = SIGUN_NM;
        this.SIGUN_CD = SIGUN_CD;
        this.BIZPLC_NM = BIZPLC_NM;
        this.LICENSG_DE = LICENSG_DE;
        this.BSN_STATE_NM = BSN_STATE_NM;
        this.CLSBIZ_DE = CLSBIZ_DE;
        this.LOCPLC_AR = LOCPLC_AR;
        this.GRAD_FACLT_DIV_NM = GRAD_FACLT_DIV_NM;
        this.MALE_ENFLPSN_CNT = MALE_ENFLPSN_CNT;
        this.YY = YY;
        this.MULTI_USE_BIZESTBL_YN = MULTI_USE_BIZESTBL_YN;
        this.TOT_FACLT_SCALE = TOT_FACLT_SCALE;
        this.FEMALE_ENFLPSN_CNT = FEMALE_ENFLPSN_CNT;
        this.BSNSITE_CIRCUMFR_DIV_NM = BSNSITE_CIRCUMFR_DIV_NM;
        this.SANITTN_INDUTYPE_NM = SANITTN_INDUTYPE_NM;
        this.SANITTN_BIZCOND_NM = SANITTN_BIZCOND_NM;
        this.TOT_EMPLY_CNT = TOT_EMPLY_CNT;
        this.REFINE_ROADNM_ADDR = REFINE_ROADNM_ADDR;
        this.REFINE_LOTNO_ADDR = REFINE_LOTNO_ADDR;
        this.REFINE_ZIP_CD = REFINE_ZIP_CD;
        this.REFINE_WGS84_LAT = new BigDecimal(REFINE_WGS84_LAT);
        this.REFINE_WGS84_LOGT = new BigDecimal(REFINE_WGS84_LOGT);
    }
}
