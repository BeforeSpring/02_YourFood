package beforespring.yourfood.batch.rawrestaurant.mapping;

import beforespring.yourfood.batch.rawrestaurant.mapping.exception.MapperProcessingException;
import beforespring.yourfood.config.ApiConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
public class OpenApiManager {

    private final ApiConfig apiConfig;

    private final XmlMapper mapper;

    public Genrestrt fetch(int page, int pageSize, String urlString) {
        String url = makeUrl(page, pageSize, urlString);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        Genrestrt data;

        try {
            data = mapper.readValue(responseEntity.getBody(), Genrestrt.class);
        } catch (JsonProcessingException e) {
            throw new MapperProcessingException(e);
        }
        return data;
    }

    private String makeUrl(int page, int pageSize, String keyword) {
        String baseUrl = "https://openapi.gg.go.kr";
        return UriComponentsBuilder.fromHttpUrl(baseUrl)
                   .path(keyword)
                   .queryParam("KEY", apiConfig.getDeveloperApiKey())
                   .queryParam("pIndex", page)
                   .queryParam("pSize", pageSize)
                   .toUriString();
    }
}
