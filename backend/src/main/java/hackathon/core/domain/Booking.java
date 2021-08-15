package hackathon.core.domain;

import java.util.Date;

public class Booking {
    private long id;
    private long land_id;

    private Date booking_datetime;
    private Date current_datetime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLand_id() {
        return land_id;
    }

    public void setLand_id(long land_id) {
        this.land_id = land_id;
    }

    public Date getBooking_datetime() {
        return booking_datetime;
    }

    public void setBooking_datetime(Date booking_datetime) {
        this.booking_datetime = booking_datetime;
    }

    public Date getCurrent_datetime() {
        return current_datetime;
    }

    public void setCurrent_datetime(Date current_datetime) {
        this.current_datetime = current_datetime;
    }
}
