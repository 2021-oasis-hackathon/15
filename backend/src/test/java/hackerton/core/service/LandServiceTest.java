package hackerton.core.service;

import hackerton.core.domain.Land;
import hackerton.core.repository.LandRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class LandServiceTest {

    @Autowired LandService landService;
    @Autowired LandRepository landRepository;

    @Test
    void 등록(){
        Land land = new Land();
        land.setAddress("광주");
        land.setArea_size(100);
        land.setMoney(100000);
        land.setCrops("옥수수");
        land.setType("field");

        Long saveId = landService.join(land);

        Land findLand = landService.findOneById(saveId);

        assertThat(land.getAddress()).isEqualTo(findLand.getAddress());

    }

    @Test
    @DisplayName("id로 찾기")
    void findId(){


    }


    @Test
    @DisplayName("주소로 찾기")
    void findAddress(){

    }

}