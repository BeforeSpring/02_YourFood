package beforespring.yourfood;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class XmlDto {
    @JacksonXmlProperty(localName = "SIGUN_NM")
    private String sigunNm;

    @JacksonXmlProperty(localName = "BIZPLC_NM")
    private String bizplcNm;

    @JacksonXmlProperty(localName = "LICENSG_DE")
    private String licensgDe;

    @JacksonXmlProperty(localName = "BSN_STATE_NM")
    private String bsnStateNm;

    @JacksonXmlProperty(localName = "CLSBIZ_DE")
    private String clsbizDe;

    @JacksonXmlProperty(localName = "MULTI_UTLZ_BIZEST_YN")
    private String multiUtlzBizestYn;

    @JacksonXmlProperty(localName = "TOT_FACLT_SCALE")
    private String totFacltScale;

    @JacksonXmlProperty(localName = "SANITTN_INDUTYPE_NM")
    private String sanittnIndutypeNm;

    @JacksonXmlProperty(localName = "SANITTN_BIZCOND_NM")
    private String sanittnBizcondNm;

    @JacksonXmlProperty(localName = "REFINE_ROADNM_ADDR")
    private String refineRoadnmAddr;

    @JacksonXmlProperty(localName = "REFINE_LOTNO_ADDR")
    private String refineLotnoAddr;

    @JacksonXmlProperty(localName = "REFINE_ZIPNO")
    private String refineZipno;

    @JacksonXmlProperty(localName = "REFINE_WGS84_LAT")
    private String refineWgs84Lat;

    @JacksonXmlProperty(localName = "REFINE_WGS84_LOGT")
    private String refineWgs84Logt;
}
