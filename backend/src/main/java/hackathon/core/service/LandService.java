package hackathon.core.service;

import hackathon.core.domain.Booking;
import hackathon.core.domain.BookingForm;
import hackathon.core.domain.Land;
import hackathon.core.domain.LandForm;

import java.awt.print.Book;
import java.util.Date;
import java.util.List;

public interface LandService {

    // 토지 등록(테스트 용으로 해봄)
    Long join(Land land);

    // id로 토지 검색해서 토지 한개 반환
    Land findOneById(Long id);

    // 토지 검색
    List<Land> findByAddress(LandForm form);

    List<Land> findAll();

    Booking saveDate(BookingForm form);

    Booking findDateById(long id);
}
