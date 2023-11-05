package beforespring.yourfood.batch.rawrestaurant.mapping;

import beforespring.yourfood.batch.rawrestaurant.mapping.exception.MapperProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpenApiManagerIntegrationTest {

    @Autowired
    private XmlMapper xmlMapper;

    /**
     * 실제 URL을 이용해서 데이터를 받은 후
     * XmlMapper를 이용해 객체로 담는 부분을 테스트합니다.
     */
    @Test
    public void testFetch() {
        String apiUrl = "/Genrestrtlunch?pIndex=1&pSize=10";
        String url = "https://openapi.gg.go.kr" + apiUrl;

        RestTemplate restTemplate = new RestTemplateBuilder().build();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        Genrestrt genrestrt = null;
        try {
            genrestrt = xmlMapper.readValue(responseEntity.getBody(), Genrestrt.class);
        } catch (Exception e) {
            throw new MapperProcessingException(e);
        }

        assertNotNull(genrestrt, "Genrestrt 객체가 null이 아니라면 API 호출 및 매핑이 성공적으로 수행된 것으로 가정");
        /**
         * 아래 반복문으로 요소 확인하는 부분은 삭제 예정
         */
        for (Row row : genrestrt.getRow()) {
            System.out.println(row.toString());
        }
    }
}