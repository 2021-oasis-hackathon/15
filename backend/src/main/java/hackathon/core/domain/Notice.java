package hackathon.core.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Notice {
    private String category;
    private String title;
    private String date;
}
