package beforespring.yourfood.batch.config;

import beforespring.yourfood.app.restaurant.domain.CuisineType;
import beforespring.yourfood.batch.rawrestaurant.fetch.fetcherimplement.gyeonggi.GyeonggiOpenApiManagerFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GyeonggiOpenApiConfig {

    @Value("${developer.api.key}")
    private String developerApiKey;


    @Bean
    public XmlMapper xmlMapper() {
        return new XmlMapper();
    }

    @Bean
    public GyeonggiOpenApiManagerFactory openApiManagerFactory() {
        Map<CuisineType, String> cuisineTypeUriMap = new HashMap<>();
        cuisineTypeUriMap.put(CuisineType.KOREAN, "/Genrestrtlunch");
        cuisineTypeUriMap.put(CuisineType.CAFE, "/Genrestrtcate");
        cuisineTypeUriMap.put(CuisineType.FUGU, "/Genrestrtfugu");
        cuisineTypeUriMap.put(CuisineType.SASHIMI, "/Genrestrtsash");
        cuisineTypeUriMap.put(CuisineType.BUFFET, "/Genrestrtbuff");
        cuisineTypeUriMap.put(CuisineType.FASTFOOD, "/Genrestrtfastfood");
        cuisineTypeUriMap.put(CuisineType.CHINESE, "/Genrestrtchifood");
        cuisineTypeUriMap.put(CuisineType.JAPANESE, "/Genrestrtjpnfood");
        cuisineTypeUriMap.put(CuisineType.SOUP, "/Genrestrtsoup");

        return new GyeonggiOpenApiManagerFactory(
            cuisineTypeUriMap,
            new RestTemplate(),
            xmlMapper(),
            developerApiKey
        );
    }
}
