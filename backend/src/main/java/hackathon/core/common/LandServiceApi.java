package hackathon.core.common;

import hackathon.core.domain.*;
import hackathon.core.service.LandService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class LandServiceApi {

    private final LandService landService;

    public LandServiceApi(LandService landService) {
        this.landService = landService;
    }

    @GetMapping(value = "/land/address")
    public List<Land> CallLandByAddress(LandForm form, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return landService.findByAddress(form);
    }

    @GetMapping(value = "/land/id")
    public Land CallLandById(@RequestParam("id") long id, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return landService.findOneById(id);
    }

    @GetMapping(value = "/land")
    public List<Land> CallLandById(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return landService.findAll();
    }

    @GetMapping(value = "/date")
    public Booking CallDateById(@RequestParam("id") long id, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return landService.findDateById(id);
    }

    @GetMapping(value = "/notice")
    public List<Notice> CallNotice(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return landService.findNotice();
    }

    @GetMapping(value = "/news")
    public News CallNews(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return landService.findNews();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/date/save")
    public Booking SaveDate(@RequestBody BookingForm form, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return landService.saveDate(form);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/land/save")
    public Land SaveLand(@RequestBody Land land, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
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
