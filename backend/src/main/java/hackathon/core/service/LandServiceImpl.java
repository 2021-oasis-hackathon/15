package hackathon.core.service;

import hackathon.core.domain.*;
import hackathon.core.repository.LandRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class LandServiceImpl implements LandService {
    private final LandRepository landRepository;

    public LandServiceImpl(LandRepository landRepository) {
        this.landRepository = landRepository;
    }

    @Override
    public List<Land> findAll() {
        List<Land> lands = landRepository.findAll();

        for (Land land : lands) {
            Coordinates coordinates = landRepository.findCoordinateById(land.getId());
            land.setX(coordinates.getX());
            land.setY(coordinates.getY());
        }

        return lands;
    }

    @Override
    public Land join(Land land) {
        landRepository.saveCoordinate(landRepository.saveLand(land));
        return land;
    }

    @Override
    public Land findOneById(Long id) {
        Land land = landRepository.findById(id);
        Coordinates coordinates = landRepository.findCoordinateById(land.getId());

        land.setX(coordinates.getX());
        land.setY(coordinates.getY());

        return land;
    }

    @Override
    public List<Land> findByAddress(LandForm form) {
        String[] landAddress = form.getAddress().split(",");

        List<Land> resultLands = new ArrayList<>();

        for (String address : landAddress) {
            List<Land> lands = landRepository.findByAddress(address);

            for (Land land : lands) {
                if (form.getMin_area() > land.getArea_size() || land.getArea_size() > form.getMax_area())
                    continue;
                if (form.getMin_money() > land.getMoney() || land.getMoney() > form.getMax_money())
                    continue;
                if (form.getType().equals(land.getType())) {
                    resultLands.add(land);
                }

            }
        }


        for (Land land : resultLands) {
            Coordinates coordinates = landRepository.findCoordinateById(land.getId());
            land.setX(coordinates.getX());
            land.setY(coordinates.getY());
        }

        return resultLands;
    }

    @Override
    public Booking saveDate(BookingForm form) {
        Booking book = new Booking();

        String[] bookDate = form.getBooking_date().split("-");
        String[] bookTime = form.getBooking_time().split(":");

        book.setPhone(form.getPhone());
        book.setLand_id(form.getLand_id());

        book.setBooking_datetime(new Date(Integer.parseInt(bookDate[0]), Integer.parseInt(bookDate[1]) - 1, Integer.parseInt(bookDate[2]),
                Integer.parseInt(bookTime[0]), Integer.parseInt(bookTime[1]), Integer.parseInt(bookTime[2])));

        return landRepository.saveDate(book, form.getLand_id());
    }

    @Override
    public Booking findDateById(long id) {
        return landRepository.findDate(id);
    }

    @Override
    public List<Notice> findNotice() {
        return landRepository.findNotice();
    }

    @Override
    public News findNews() {
        return landRepository.findNews();
    }
}
