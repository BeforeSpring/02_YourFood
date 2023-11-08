package beforespring.yourfood.batch.rawrestaurant.mapping;

import beforespring.yourfood.batch.rawrestaurant.mapping.exception.MapperProcessingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
@AllArgsConstructor
public class OpenApiManager {
    private final String url;
    private final XmlMapper mapper;

    public Genrestrt fetch() {
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
}
