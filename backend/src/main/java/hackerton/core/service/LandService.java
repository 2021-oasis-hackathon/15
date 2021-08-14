package hackerton.core.service;

import hackerton.core.domain.Land;
import hackerton.core.domain.LandForm;

import java.util.List;

public interface LandService {

    // 토지 등록(테스트 용으로 해봄)
    Long join(Land land);

    // id로 토지 검색해서 토지 한개 반환
    Land findOneById(Long id);

    // 토지 검색
    List<Land> findByAddress(LandForm form);

    List<Land> findAll();
}
