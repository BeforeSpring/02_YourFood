package beforespring.yourfood.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration @Getter
public class ApiConfig {
    @Value("${developer.api.key}")
    private String developerApiKey;
}
