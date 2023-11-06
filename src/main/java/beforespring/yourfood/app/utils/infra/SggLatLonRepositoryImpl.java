package beforespring.yourfood.app.utils.infra;

import beforespring.yourfood.app.utils.SggLatLon;
import beforespring.yourfood.app.utils.SggLatLonRepository;
import beforespring.yourfood.config.SggLatLonConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SggLatLonRepositoryImpl implements SggLatLonRepository {
    private final SggLatLonConfig sggLatLonConfig;

    @Override
    public List<SggLatLon> findAll() {
        return Collections.unmodifiableList(sggLatLonConfig.getSggLatLons());
    }
}
