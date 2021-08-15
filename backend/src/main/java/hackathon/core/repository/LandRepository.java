package hackathon.core.repository;

import hackathon.core.domain.Booking;
import hackathon.core.domain.BookingForm;
import hackathon.core.domain.Coordinates;
import hackathon.core.domain.Land;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface LandRepository {
    Land saveLand(Land land);

    Land savePicuture(Land land);

    Land saveCoordinate(Land land);

    Land findById(long id);

    List<Land> findByAddress(String address);

    List<String> findPictureById(Long land_id);

    List<Land> findAll();

    Coordinates findCoordinateById(Long land_id);

    Booking saveDate(Booking book, long land_id);

    Booking findDate(long id);
}
