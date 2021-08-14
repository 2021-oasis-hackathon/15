package hackathon.core.repository;

import hackathon.core.domain.Land;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LandRepository {
    Land save(Land land);

    Land findById(long id);

    List<Land> findByAddress(String address);

    List<String> findPictureById(Long land_id);

    List<Land> findAll();
}
