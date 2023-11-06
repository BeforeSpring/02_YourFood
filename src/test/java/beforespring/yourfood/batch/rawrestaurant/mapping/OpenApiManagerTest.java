package beforespring.yourfood.batch.rawrestaurant.mapping;

import beforespring.yourfood.config.ApiConfig;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OpenApiManagerTest {

    private final RestTemplate restTemplate = new RestTemplate();

    @Mock
    private XmlMapper xmlMapper;

    private OpenApiManager openApiManager;

    @BeforeEach
    public void setUp() {
        ApiConfig apiConfig = new ApiConfig();
        openApiManager = new OpenApiManager(apiConfig, xmlMapper);
    }

    @Test
    public void testFetch() throws Exception {
        // 가짜 응답 데이터 로드
        ClassPathResource resource = new ClassPathResource("xml/list.xml");
        InputStream fakeResponseStream = resource.getInputStream();
        String fakeResponse = new String(fakeResponseStream.readAllBytes());

        // 가짜 Genrestrt 객체 생성
        Genrestrt fakeGenrestrt = new Genrestrt();

        // RestTemplate 모의 객체 설정
        when(restTemplate.getForEntity(any(String.class), any(Class.class))).thenReturn(ResponseEntity.ok(fakeResponse));
        // xmlMapper 모의 객체 설정
        when(xmlMapper.readValue(fakeResponse, Genrestrt.class)).thenReturn(fakeGenrestrt);

        // fetch 메서드 호출
        Genrestrt result = openApiManager.fetch(1, 10);

        // 검증
        assertEquals(fakeGenrestrt, result);
    }
}
