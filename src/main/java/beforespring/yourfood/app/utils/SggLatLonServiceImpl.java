package beforespring.yourfood.app.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SggLatLonServiceImpl implements SggLatLonService {
    private final SggLatLonRepository sggLatLonRepository;

    @Override
    public List<SggLatLon> getAllSggLatLon() {
        return sggLatLonRepository.findAll();
    }
}
