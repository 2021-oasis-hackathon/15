package hackathon.core.common;

import hackathon.core.domain.*;
import hackathon.core.service.LandService;
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

    @GetMapping(value = "/date")
    public Booking CallDateById(@RequestParam("id") long id) {
        return landService.findDateById(id);
    }

    @GetMapping(value = "/notice")
    public List<Notice> CallNotice() {
        return landService.findNotice();
    }

    @GetMapping(value = "/news")
    public News CallNews() {
        return landService.findNews();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/date/save")
    public Booking SaveDate(@RequestBody BookingForm form) {
        return landService.saveDate(form);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/land/save")
    public Land SaveLand(@RequestBody Land land) {
        return landService.join(land);
    }


//    {
//            "address":"",
//            "area_size":,
//            "money":,
//            "crops":"",
//            "type":"",
//            "tractor":"",
//            "rice_planting":"",
//            "fluid_fertilizer":"",
//            "combine":"",
//            "tree_crush":"",
//            "picture":["","",""],
//        "x":,
//            "y":
//    }


}
