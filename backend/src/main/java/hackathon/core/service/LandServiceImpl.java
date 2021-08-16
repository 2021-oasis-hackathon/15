package hackathon.core.service;

import hackathon.core.domain.*;
import hackathon.core.repository.LandRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class LandServiceImpl implements LandService {
    private final LandRepository landRepository;
    private final CoordinateConversionService coordinateConversionService;

    public LandServiceImpl(LandRepository landRepository, CoordinateConversionService coordinateConversionService) {
        this.landRepository = landRepository;
        this.coordinateConversionService = coordinateConversionService;
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
    public Land join(Land land) throws Exception {
        Coordinates coordinates = coordinateConversionService.getJsonData(land.getAddress());
        land.setX(coordinates.getX());
        land.setY(coordinates.getY());
        landRepository.saveCoordinate(landRepository.saveLand(land));
        return land;
    }

    @Override
    public Land findOneById(Long id) {
        Optional<Land> land = landRepository.findById(id);
        land.ifPresentOrElse(l -> {
            Coordinates coordinates = landRepository.findCoordinateById(l.getId());

            l.setX(coordinates.getX());
            l.setY(coordinates.getY());
        }, () -> {
            throw new NoSuchElementException("id에 맞는 토지를 찾을 수 없습니다.");
        });
        return land.get();
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
        book.setBooking(form.getBooking_date() + " " + form.getBooking_time());

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
        return landRepository.findDate(id).get();
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
