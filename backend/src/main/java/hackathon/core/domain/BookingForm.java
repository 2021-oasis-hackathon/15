package hackathon.core.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BookingForm {

    private long land_id;
    private String booking_date;
    private String booking_time;
    private String phone;

}
