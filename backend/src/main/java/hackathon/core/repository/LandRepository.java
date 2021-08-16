package hackathon.core.repository;

import hackathon.core.domain.*;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface LandRepository {
    Land saveLand(Land land);

    Land saveCoordinate(Land land);

    Optional<Land> findById(long id);

    List<Land> findByAddress(String address);

    List<Land> findAll();

    Coordinates findCoordinateById(Long land_id);

    Booking saveDate(Booking book, long land_id);

    Optional<Booking> findDate(long id);

    List<Notice> findNotice();

    News findNews();
}
