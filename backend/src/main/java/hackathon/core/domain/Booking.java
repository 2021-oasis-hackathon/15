package hackathon.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Booking {
    private long id;
    private long land_id;

    private Date booking_datetime;
    private Date current_datetime;
    private String booking;
    private String current;
    private String phone;

}
