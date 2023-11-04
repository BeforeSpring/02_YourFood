package beforespring.yourfood.batch.rawrestaurant.mapping;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Row {
    @JacksonXmlProperty(localName = "SIGUN_NM") String sigunNm;

    @JacksonXmlProperty(localName = "SIGUN_CD") String sigunCd;

    @JacksonXmlProperty(localName = "BIZPLC_NM") String bizplcNm;

    @JacksonXmlProperty(localName = "LICENSG_DE") String licensgDe;

    @JacksonXmlProperty(localName = "BSN_STATE_NM") String bsnStateNm;

    @JacksonXmlProperty(localName = "CLSBIZ_DE") String clsbizDe;

    @JacksonXmlProperty(localName = "LOCPLC_AR") String locplcAr;

    @JacksonXmlProperty(localName = "GRAD_FACLT_DIV_NM") String gradFacltDivNm;

    @JacksonXmlProperty(localName = "MALE_ENFLPSN_CNT") String maleEnflpsnCnt;

    @JacksonXmlProperty(localName = "YY") String yy;

    @JacksonXmlProperty(localName = "MULTI_USE_BIZESTBL_YN") String multiUseBizestblYn;

    @JacksonXmlProperty(localName = "GRAD_DIV_NM") String gradDivNm;

    @JacksonXmlProperty(localName = "TOT_FACLT_SCALE") String totFacltScale;

    @JacksonXmlProperty(localName = "FEMALE_ENFLPSN_CNT") String femaleEnflpsnCnt;

    @JacksonXmlProperty(localName = "BSNSITE_CIRCUMFR_DIV_NM") String bsnsiteCircumfrDivNm;

    @JacksonXmlProperty(localName = "SANITTN_INDUTYPE_NM") String sanittnIndutypeNm;

    @JacksonXmlProperty(localName = "SANITTN_BIZCOND_NM") String sanittnBizcondNm;

    @JacksonXmlProperty(localName = "TOT_EMPLY_CNT") String totEmplyCnt;

    @JacksonXmlProperty(localName = "REFINE_LOTNO_ADDR") String refineLotnoAddr;

    @JacksonXmlProperty(localName = "REFINE_ROADNM_ADDR") String refineRoadnmAddr;

    @JacksonXmlProperty(localName = "REFINE_ZIP_CD") String refineZipCd;

    @JacksonXmlProperty(localName = "REFINE_WGS84_LOGT") String refineWgs84Logt;

    @JacksonXmlProperty(localName = "REFINE_WGS84_LAT") String refineWgs84Lat;
}

