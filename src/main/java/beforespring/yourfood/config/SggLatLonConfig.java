package beforespring.yourfood.config;

import beforespring.yourfood.app.utils.SggLatLon;
import beforespring.yourfood.app.utils.SggLatLonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SggLatLonConfig {
    private final SggLatLonRepository sggLatLonRepository;

    @Bean
    @Profile("withSggData")
    public void run() {
        try {
            Resource resource = new ClassPathResource("sggLatLon.csv");

            List<SggLatLon> sggLatLons = parseSggData(resource);
            sggLatLonRepository.saveAll(sggLatLons);
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }

    /**
     * sggLatLon.csv 파일을 엔티티로 변환하기 위한 파서
     *
     * @param resource 파싱할 파일
     * @return 생성된 엔티티 객체 List
     * @throws IOException 입출력 예외
     */
    private List<SggLatLon> parseSggData(Resource resource) throws IOException {
        List<SggLatLon> sggLatLons = new ArrayList<>();

        try (InputStream inputStream = resource.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            reader.readLine(); // 첫 번째 라인을 읽어넘기 (헤더)

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(","); // CSV 파일에서 데이터를 읽어옴 (쉼표로 분리)

                sggLatLons.add(
                    SggLatLon.builder()
                        .siDo(data[0])
                        .siGunGu(data[1])
                        .lon(data[2])
                        .lat(data[3]).build()
                );
            }
        }
        return sggLatLons;
    }
}
