package hackathon.core.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LandForm {
    String address;
    int min_area;
    int max_area;
    long min_money;
    long max_money;
    String type;
}
