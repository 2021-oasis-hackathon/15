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

    public String getBooking() {
        return booking;
    }

    public void setBooking(String booking) {
        this.booking = booking;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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
