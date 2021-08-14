package hackathon.core.service;

import hackathon.core.domain.Land;
import hackathon.core.domain.LandForm;
import hackathon.core.repository.LandRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LandServiceImpl implements LandService {
    private final LandRepository landRepository;

    public LandServiceImpl(LandRepository landRepository) {
        this.landRepository = landRepository;
    }

    @Override
    public List<Land> findAll() {
        return landRepository.findAll();
    }

    @Override
    public Long join(Land land) {
        landRepository.save(land);
        return land.getId();
    }

    @Override
    public Land findOneById(Long id) {
        Land land = landRepository.findById(id);
        List<String> pictures = landRepository.findPictureById(id);
        land.setPicture(pictures);

        return land;
    }

    @Override
    public List<Land> findByAddress(LandForm form) {
        List<Land> lands = landRepository.findByAddress(form.getAddress());
        List<Land> resultLands = new ArrayList<>();


        for (Land land : lands) {
            if (form.getMin_area() > land.getArea_size() || land.getArea_size() > form.getMax_area())
                continue;
            if (form.getMin_money() > land.getMoney() || land.getMoney() > form.getMax_money())
                continue;
            if (form.getType().equals(land.getType()))
                resultLands.add(land);
        }


        for (Land land : resultLands) {
            List<String> pictures = landRepository.findPictureById(land.getId());
            land.setPicture(pictures);
        }

        return resultLands;
    }


}
