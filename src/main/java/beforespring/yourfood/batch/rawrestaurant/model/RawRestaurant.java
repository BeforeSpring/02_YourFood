package beforespring.yourfood.batch.rawrestaurant.model;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
            name = "idx__raw_restaurant__BIZPLC_NM__REFINE_ROADNM_ADDR",
            columnList = "BIZPLC_NM, REFINE_ROADNM_ADDR",
            unique = true
        ),
        @Index(
            name = "idx__raw_restaurant__crucial_info_fetched_at",
            columnList = "crucial_info_fetched_at"
        )
    }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RawRestaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private RawRestaurantId rawRestaurantId;
    private String SIGUN_NM;
    private String SIGUN_CD;
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
    private String REFINE_LOTNO_ADDR;
    private String REFINE_ZIP_CD;
    private String REFINE_WGS84_LAT;
    private String REFINE_WGS84_LOGT;

    /**
     * 변경된 값이 Restaurant에 반영되었으면 true, 아직 반영되지 않았으면 false
     */
    @Column(name = "has_non_updated_info")
    private boolean hasNonUpdatedInfo;

    /**
     * 식당 정보가 변동된 날짜. 서비스에 필요한 정보를 판별하는데 사용됨. IS_UPDATED 등, 식당 정보와 관련 없는 정보가 변동될 때에는 업데이트 되면 안 됨.
     */
    @Column(name = "fetched_at")
    private LocalDateTime fetchedAt;

    /**
     * 서비스에 필요한 주요 정보가 업데이트된 시각. 레스토랑 정보 업데이트 배치 작업의 커서로 활용됨.
     */
    @Column(name = "crucial_info_fetched_at")
    private LocalDateTime crucialInfoFetchedAt;

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
        String REFINE_WGS84_LOGT,
        Boolean hasNonUpdatedInfo,
        LocalDateTime crucialInfoFetchedAt,
        LocalDateTime fetchedAt
    ) {
        this.id = id;
        this.rawRestaurantId = new RawRestaurantId(BIZPLC_NM, REFINE_ROADNM_ADDR);
        this.SIGUN_NM = SIGUN_NM;
        this.SIGUN_CD = SIGUN_CD;
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
        this.REFINE_LOTNO_ADDR = REFINE_LOTNO_ADDR;
        this.REFINE_ZIP_CD = REFINE_ZIP_CD;
        this.REFINE_WGS84_LAT = REFINE_WGS84_LAT;
        this.REFINE_WGS84_LOGT = REFINE_WGS84_LOGT;

        LocalDateTime now = LocalDateTime.now();

        // null인 경우 외부에서 읽어온 값.
        if (hasNonUpdatedInfo != null) {
            this.hasNonUpdatedInfo = hasNonUpdatedInfo;
        }
        // null인 경우 외부에서 읽어온 값.
        this.crucialInfoFetchedAt = crucialInfoFetchedAt == null ? now : crucialInfoFetchedAt;
        // null인 경우 외부에서 읽어온 값.
        this.fetchedAt = fetchedAt == null ? now : fetchedAt;
    }


    /**
     * DB에서 불러온 엔티티의 값을 외부에서 새로 불러온 엔티티의 값으로 업데이트함. 외부에서 새로 불러온 객체의 값을 업데이트해선 안 됨. (잠재적 버그 발생 방지)
     *
     * @param fetched 새로 fetch 받아온 데이터
     */
    public void updateDataFrom(RawRestaurant fetched) {
        // id가 일치하지 않는 경우 업데이트 불가
        if (!this.rawRestaurantId.equals(fetched.getRawRestaurantId())) {
            throw new IllegalStateException("rawRestaurantId가 일치하지 않음");
        }
        // 외부 api를 통해 새로 불러온 객체가 아닌, DB에서 가져온 객체만 호출 가능
        if (this.isNewlyFetched()) {
            throw new IllegalCallerException("외부에서 새로 불러온 객체가 아닌, DB에서 가져온 객체만 호출 가능");
        }
        // 업데이트할 내용이 없는 경우 바로 return
        if (!this.isOutdatedCompareTo(fetched)) {
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        this.fetchedAt = now;  // fetch 된 시간 업데이트

        if (this.isCrucialInfoOutdatedCompareTo(fetched)) {
            this.hasNonUpdatedInfo = true;
            this.crucialInfoFetchedAt = now;  // 주요 정보 변경 시각 업데이트
        }
        this.LICENSG_DE = fetched.getLICENSG_DE();
        this.BSN_STATE_NM = fetched.getBSN_STATE_NM();
        this.CLSBIZ_DE = fetched.getCLSBIZ_DE();
        this.LOCPLC_AR = fetched.getLOCPLC_AR();
        this.GRAD_FACLT_DIV_NM = fetched.getGRAD_FACLT_DIV_NM();
        this.MALE_ENFLPSN_CNT = fetched.getMALE_ENFLPSN_CNT();
        this.YY = fetched.getYY();
        this.MULTI_USE_BIZESTBL_YN = fetched.getMULTI_USE_BIZESTBL_YN();
        this.TOT_FACLT_SCALE = fetched.getTOT_FACLT_SCALE();
        this.FEMALE_ENFLPSN_CNT = fetched.getFEMALE_ENFLPSN_CNT();
        this.BSNSITE_CIRCUMFR_DIV_NM = fetched.getBSNSITE_CIRCUMFR_DIV_NM();
        this.SANITTN_INDUTYPE_NM = fetched.getSANITTN_INDUTYPE_NM();
        this.SANITTN_BIZCOND_NM = fetched.getSANITTN_BIZCOND_NM();
    }

    /**
     * 이전 RawRestaurant와 비교하여 정보가 변경되었는지 여부. 변경 불가능한 값은 체크하지 않음.
     *
     * @param oldData 비교할 대상
     * @return 업데이트 되었는지 여부
     */
    public boolean isOutdatedCompareTo(RawRestaurant oldData) {
        // 외부 api를 통해 새로 불러온 객체가 아닌, DB에서 가져온 객체만 호출 가능
        if (this.isNewlyFetched()) {
            throw new IllegalCallerException("외부에서 새로 불러온 객체가 아닌, DB에서 가져온 객체만 호출 가능");
        }
        return !(Objects.equals(getRawRestaurantId(), oldData.getRawRestaurantId())
                     && Objects.equals(getSIGUN_NM(), oldData.getSIGUN_NM())
                     && Objects.equals(getSIGUN_CD(), oldData.getSIGUN_CD())
                     && Objects.equals(getLICENSG_DE(), oldData.getLICENSG_DE())
                     && Objects.equals(getBSN_STATE_NM(), oldData.getBSN_STATE_NM())
                     && Objects.equals(getCLSBIZ_DE(), oldData.getCLSBIZ_DE())
                     && Objects.equals(getLOCPLC_AR(), oldData.getLOCPLC_AR())
                     && Objects.equals(getGRAD_FACLT_DIV_NM(), oldData.getGRAD_FACLT_DIV_NM())
                     && Objects.equals(getMALE_ENFLPSN_CNT(), oldData.getMALE_ENFLPSN_CNT())
                     && Objects.equals(getYY(), oldData.getYY()) && Objects.equals(
            getMULTI_USE_BIZESTBL_YN(), oldData.getMULTI_USE_BIZESTBL_YN()) && Objects.equals(
            getTOT_FACLT_SCALE(), oldData.getTOT_FACLT_SCALE()) && Objects.equals(
            getFEMALE_ENFLPSN_CNT(), oldData.getFEMALE_ENFLPSN_CNT()) && Objects.equals(
            getBSNSITE_CIRCUMFR_DIV_NM(), oldData.getBSNSITE_CIRCUMFR_DIV_NM())
                     && Objects.equals(getSANITTN_INDUTYPE_NM(), oldData.getSANITTN_INDUTYPE_NM())
                     && Objects.equals(getSANITTN_BIZCOND_NM(), oldData.getSANITTN_BIZCOND_NM())
                     && Objects.equals(getTOT_EMPLY_CNT(), oldData.getTOT_EMPLY_CNT()));
    }

    /**
     * 서비스에서 필요로 하는 값 중, 변경된 값이 있는지 확인함. 현재 필요한 값은 영업 여부 값 뿐임.
     *
     * @param newlyFetched
     * @return 영업 여부가 변경되었는지.
     */
    public boolean isCrucialInfoOutdatedCompareTo(RawRestaurant newlyFetched) {
        // 외부 api를 통해 새로 불러온 객체가 아닌, DB에서 가져온 객체만 호출 가능
        if (this.isNewlyFetched()) {
            throw new IllegalCallerException("외부에서 새로 불러온 객체가 아닌, DB에서 가져온 객체만 호출 가능");
        }
        return !(Objects.equals(getBSN_STATE_NM(), newlyFetched.getBSN_STATE_NM()));
    }

    public boolean isNewlyFetched() {
        return this.id == null;
    }
}
