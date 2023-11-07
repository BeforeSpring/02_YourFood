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

    private final Map<String, String> keywords;

    @Bean
    public XmlMapper xmlMapper() {
        return new XmlMapper();
    }

    public String getApiKeyword(String keyIdentifier) {
        // Retrieve the API key using the key identifier
        return keywords.get(keyIdentifier);
    }

    public ApiConfig() {
        keywords = new HashMap<>();
        keywords.put("lunch", "/Genrestrtlunch");
        keywords.put("cafe", "/Genrestrtcate");
        keywords.put("fugu", "/Genrestrtfugu");
        keywords.put("sashimi", "/Genrestrtsash");
        keywords.put("buffet", "/Genrestrtbuff");
        keywords.put("fastfood", "/Genrestrtfastfood");
        keywords.put("china", "/Genrestrtchifood");
        keywords.put("japan", "/Genrestrtjpnfood");
        keywords.put("soup", "/Genrestrtsoup");
    }
}
