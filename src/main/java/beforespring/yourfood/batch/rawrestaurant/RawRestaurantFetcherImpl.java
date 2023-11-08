package beforespring.yourfood.batch.rawrestaurant;

import beforespring.yourfood.app.restaurant.domain.CuisineType;
import beforespring.yourfood.batch.rawrestaurant.mapping.Genrestrt;
import beforespring.yourfood.batch.rawrestaurant.mapping.OpenApiManager;
import beforespring.yourfood.batch.rawrestaurant.mapping.OpenApiManagerFactory;
import beforespring.yourfood.batch.rawrestaurant.model.RawRestaurant;

import java.util.List;

/**
 * 해당 fetcher는 다른 공공데이터 url로 사용할 수 없기 떄문에 class 명을 경기도 관련으로 바꿔야됨
 * <p>
 * Refactoring 필요함
 */
public class RawRestaurantFetcherImpl implements RawRestaurantFetcher {
    private final CuisineType cuisineType;

    private final OpenApiManager openApiManager;


    public RawRestaurantFetcherImpl(OpenApiManagerFactory openApiManagerFactory, CuisineType cuisineType) {
        this.cuisineType = cuisineType;
        this.openApiManager = openApiManagerFactory.createOpenApiManager(cuisineType);
    }

    //시도, 퀴신 타입 추가
    @Override
    public RawRestaurantFetchResult find(int page, int pageSize) {
        Genrestrt genrestrt = openApiManager.fetch();

        List<RawRestaurant> rawRestaurants = genrestrt.getRow().stream()
                                                 .map(row -> RawRestaurant.builder()
                                                                 .SIGUN_NM(row.getSigunNm())
                                                                 .SIGUN_CD(row.getSigunCd())
                                                                 .BIZPLC_NM(row.getBizplcNm())
                                                                 .LICENSG_DE(row.getLicensgDe())
                                                                 .BSN_STATE_NM(row.getBsnStateNm())
                                                                 .CLSBIZ_DE(row.getClsbizDe())
                                                                 .LOCPLC_AR(row.getLocplcAr())
                                                                 .GRAD_FACLT_DIV_NM(row.getGradFacltDivNm())
                                                                 .MALE_ENFLPSN_CNT(row.getMaleEnflpsnCnt())
                                                                 .YY(row.getYy())
                                                                 .MULTI_USE_BIZESTBL_YN(row.getMultiUseBizestblYn())
                                                                 .TOT_FACLT_SCALE(row.getTotFacltScale())
                                                                 .FEMALE_ENFLPSN_CNT(row.getFemaleEnflpsnCnt())
                                                                 .BSNSITE_CIRCUMFR_DIV_NM(row.getBsnsiteCircumfrDivNm())
                                                                 .SANITTN_INDUTYPE_NM(row.getSanittnIndutypeNm())
                                                                 .SANITTN_BIZCOND_NM(row.getSanittnBizcondNm())
                                                                 .TOT_EMPLY_CNT(row.getTotEmplyCnt())
                                                                 .REFINE_LOTNO_ADDR(row.getRefineLotnoAddr())
                                                                 .REFINE_ROADNM_ADDR(row.getRefineRoadnmAddr())
                                                                 .REFINE_ZIP_CD(row.getRefineZipCd())
                                                                 .REFINE_WGS84_LAT(row.getRefineWgs84Lat())
                                                                 .REFINE_WGS84_LOGT(row.getRefineWgs84Logt())
                                                                 .sido("경기도")
                                                                 .cuisineType(cuisineType)
                                                                 .build())
                                                 .toList();

        return new RawRestaurantFetchResult(page, pageSize, genrestrt.getHead().getListTotalCount(), rawRestaurants);
    }
}
