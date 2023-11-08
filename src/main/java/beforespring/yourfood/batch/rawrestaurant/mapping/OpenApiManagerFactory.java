package beforespring.yourfood.batch.rawrestaurant.mapping;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

public class OpenApiManagerFactory {

    private final Map<String, String> cuisineTypeNames;
    private final XmlMapper mapper;
    private final String devKey;

    public OpenApiManager createOpenApiManager(int page, int pageSize, String cuisineTypeName) {
        String baseUrl = "https://openapi.gg.go.kr";
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                         .path(cuisineTypeNames.get(cuisineTypeName))
                         .queryParam("KEY", devKey)
                         .queryParam("pIndex", page)
                         .queryParam("pSize", pageSize)
                         .toUriString();
        return new OpenApiManager(url, mapper);
    }

    public OpenApiManagerFactory(Map<String, String> cuisineTypeNames, XmlMapper mapper, String devKey) {
        this.cuisineTypeNames = cuisineTypeNames;
        this.mapper = mapper;
        this.devKey = devKey;
    }
}
