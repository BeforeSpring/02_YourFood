package beforespring.yourfood.batch.rawrestaurant.mapping;

import beforespring.yourfood.config.ApiConfig;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OpenApiManagerFactory {

    private final ApiConfig apiConfig;
    private final XmlMapper mapper;

    public OpenApiManager createOpenApiManager() {
        return new OpenApiManager(apiConfig, mapper);
    }
}