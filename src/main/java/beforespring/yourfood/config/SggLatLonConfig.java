package beforespring.yourfood.config;

import beforespring.yourfood.app.utils.SggLatLon;
import beforespring.yourfood.config.exception.CsvIOException;
import lombok.Getter;
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

@Configuration
@Getter
public class SggLatLonConfig {
    private final List<SggLatLon> sggLatLons = new ArrayList<>();

    @Bean
    @Profile("withSggData")
    public void run() {
        Resource resource = new ClassPathResource("sggLatLon.csv");
        parseSggCsv(resource);
    }

    /**
     * sggLatLon.csv 파일을 SggLatLon List로 변환하기 위한 파서
     *
     * @param resource 파싱할 파일
     */
    private void parseSggCsv(Resource resource) {
        try {
            InputStream inputStream = resource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            reader.readLine();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                sggLatLons.add(
                    SggLatLon.builder()
                        .siDo(data[0])
                        .siGunGu(data[1])
                        .lon(data[2])
                        .lat(data[3]).build()
                );
            }
        } catch (IOException e) {
            throw new CsvIOException(e);
        }
    }
}
