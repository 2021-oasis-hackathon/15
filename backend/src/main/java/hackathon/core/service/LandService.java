package hackathon.core.service;

import hackathon.core.domain.*;

import java.util.List;

public interface LandService {

    // 토지 등록
    Land join(Land land);

    // id로 토지 검색해서 토지 한개 반환
    Land findOneById(Long id);

    // 토지 검색
    List<Land> findByAddress(LandForm form);

    // 전부 다 검색
    List<Land> findAll();

    // 예약 등록
    Booking saveDate(BookingForm form);

    // id(예약번호)로 예약 검색
    Booking findDateById(long id);

    List<Notice> findNotice();

    News findNews();
}
