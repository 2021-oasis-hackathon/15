package hackathon.core.common;

import hackathon.core.domain.*;
import hackathon.core.service.LandService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
public class LandServiceApi {

    private final LandService landService;

    public LandServiceApi(LandService landService) {
        this.landService = landService;
    }

    @GetMapping(value = "/land/address")
    public List<Land> CallLandByAddress(LandForm form, HttpServletResponse response) {
        return landService.findByAddress(form);
    }

    @GetMapping(value = "/land/id")
    public Land CallLandById(@RequestParam("id") long id, HttpServletResponse response) {
        return landService.findOneById(id);
    }

    @GetMapping(value = "/land")
    public List<Land> CallLandById(HttpServletResponse response) {
        return landService.findAll();
    }

    @GetMapping(value = "/date")
    public Booking CallDateById(@RequestParam("id") long id, HttpServletResponse response) {
        return landService.findDateById(id);
    }

    @GetMapping(value = "/notice")
    public List<Notice> CallNotice(HttpServletResponse response) {
        return landService.findNotice();
    }

    @GetMapping(value = "/news")
    public News CallNews(HttpServletResponse response) {
        return landService.findNews();
    }


    @PostMapping(value = "/date/save")
    public Booking SaveDate(@RequestBody BookingForm form, HttpServletResponse response) {
        return landService.saveDate(form);
    }

    @PostMapping(value = "/land/save")
    public Land SaveLand(@RequestBody Land land, HttpServletResponse response) throws Exception {
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
