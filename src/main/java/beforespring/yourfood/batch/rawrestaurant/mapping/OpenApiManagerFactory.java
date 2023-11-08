package beforespring.yourfood.batch.rawrestaurant.mapping;

import beforespring.yourfood.app.restaurant.domain.CuisineType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

public class OpenApiManagerFactory {

    private final Map<CuisineType, String> cuisineTypeNames;

    private final RestTemplate restTemplate;
    private final XmlMapper mapper;
    private final String devKey;

    public OpenApiManager createOpenApiManager(CuisineType cuisineType) {
        String url = createUrl(cuisineType);
        return new OpenApiManager(url, mapper, restTemplate);
    }

    public String createUrl(CuisineType cuisineType) {
        String baseUrl = "https://openapi.gg.go.kr";
        return UriComponentsBuilder.fromHttpUrl(baseUrl)
                   .path(cuisineTypeNames.get(cuisineType))
                   .queryParam("KEY", devKey)
                   .toUriString();
    }

    public OpenApiManagerFactory(Map<CuisineType, String> cuisineTypeNames, RestTemplate restTemplate, XmlMapper mapper, String devKey) {
        this.cuisineTypeNames = cuisineTypeNames;
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        this.devKey = devKey;
    }
}
