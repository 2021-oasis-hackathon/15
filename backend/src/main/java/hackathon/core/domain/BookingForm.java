package hackathon.core.domain;

import org.springframework.stereotype.Component;

@Component
public class BookingForm {

    private long land_id;
    private String booking_date;
    private String booking_time;

    public long getLand_id() {
        return land_id;
    }

    public void setLand_id(long land_id) {
        this.land_id = land_id;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public String getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(String booking_time) {
        this.booking_time = booking_time;
    }
}
