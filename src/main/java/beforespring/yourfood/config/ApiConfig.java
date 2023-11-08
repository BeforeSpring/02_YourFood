package beforespring.yourfood.config;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Getter
public class ApiConfig {
    @Value("${developer.api.key}")
    private String developerApiKey;

    private final Map<String, String> CuisineTypeNames;

    @Bean
    public XmlMapper xmlMapper() {
        return new XmlMapper();
    }

    public ApiConfig() {
        CuisineTypeNames = new HashMap<>();
        CuisineTypeNames.put("lunch", "/Genrestrtlunch");
        CuisineTypeNames.put("cafe", "/Genrestrtcate");
        CuisineTypeNames.put("fugu", "/Genrestrtfugu");
        CuisineTypeNames.put("sashimi", "/Genrestrtsash");
        CuisineTypeNames.put("buffet", "/Genrestrtbuff");
        CuisineTypeNames.put("fastfood", "/Genrestrtfastfood");
        CuisineTypeNames.put("china", "/Genrestrtchifood");
        CuisineTypeNames.put("japan", "/Genrestrtjpnfood");
        CuisineTypeNames.put("soup", "/Genrestrtsoup");
    }
}
