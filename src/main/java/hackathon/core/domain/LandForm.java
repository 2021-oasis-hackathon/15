package hackathon.core.domain;

import org.springframework.stereotype.Component;

@Component
public class LandForm {
    String address;
    int min_area;
    int max_area;
    long min_money;
    long max_money;
    String type;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMin_area() {
        return min_area;
    }

    public void setMin_area(int min_area) {
        this.min_area = min_area;
    }

    public int getMax_area() {
        return max_area;
    }

    public void setMax_area(int max_area) {
        this.max_area = max_area;
    }

    public long getMin_money() {
        return min_money;
    }

    public void setMin_money(long min_money) {
        this.min_money = min_money;
    }

    public long getMax_money() {
        return max_money;
    }

    public void setMax_money(long max_money) {
        this.max_money = max_money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
