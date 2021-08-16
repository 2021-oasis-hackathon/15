package hackathon.core.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Land {

    private Long id;                // id
    private String address;         // 주소
    private int area_size;          // 면적, 평수
    private long money;             // 연간 리스 비용
    private int incentive;          // 인센티브
    private String picture;         // 사진
    private String crops;           // 재배 작물
    private String type;            // 공부 지목
    private String tractor;         // 트랙터
    private String rice_planting;   // 이앙기
    private String fluid_fertilizer;// 액상비료 살포기
    private String combine;         // 콤바인
    private String tree_crush;      // 나무 분쇄기
    private double x;               // x 좌표
    private double y;               // y 좌표

}
