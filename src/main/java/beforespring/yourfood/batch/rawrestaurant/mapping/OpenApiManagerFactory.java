package beforespring.yourfood.batch.rawrestaurant.mapping;

import beforespring.yourfood.config.ApiConfig;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class OpenApiManagerFactory {

    private final ApiConfig apiConfig;
    private final XmlMapper mapper;

    public OpenApiManager createOpenApiManager(int page, int pageSize, String keyword) {
        String baseUrl = "https://openapi.gg.go.kr";
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                             .path(keyword)
                             .queryParam("KEY", apiConfig.getDeveloperApiKey())
                             .queryParam("pIndex", page)
                             .queryParam("pSize", pageSize)
                             .toUriString();
        return new OpenApiManager(url, mapper);
    }
}
