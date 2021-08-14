package hackerton.core.common;


import hackerton.core.domain.Land;
import hackerton.core.domain.LandForm;
import hackerton.core.service.LandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LandServiceApi {

    private final LandService landService;

    public LandServiceApi(LandService landService) {
        this.landService = landService;
    }

    @GetMapping(value = "/land/address")
    public List<Land> CallLandByAddress(LandForm form) {
        return landService.findByAddress(form);
    }

    @GetMapping(value = "/land/id")
    public Land CallLandById(@RequestParam("id") long id) {
        return landService.findOneById(id);
    }

    @GetMapping(value = "/land")
    public List<Land> CallLandById() {
        return landService.findAll();
    }
}
