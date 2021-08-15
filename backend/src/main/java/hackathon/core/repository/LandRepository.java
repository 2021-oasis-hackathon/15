package hackathon.core.repository;

import hackathon.core.domain.*;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface LandRepository {
    Land saveLand(Land land);

    Land saveCoordinate(Land land);

    Land findById(long id);

    List<Land> findByAddress(String address);

    List<Land> findAll();

    Coordinates findCoordinateById(Long land_id);

    Booking saveDate(Booking book, long land_id);

    Booking findDate(long id);

    List<Notice> findNotice();

    News findNews();
}
