package hackathon.core.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Land {

    private Long id;                // id
    private String address;         // 주소
    private int area_size;          // 면적, 평수
    private long money;             // 연간 리스 비용
    private String crops;           // 재배 작물
    private String type;            // 공부 지목
    private String tractor;         // 트랙터
    private String rice_planting;   // 이앙기
    private String fluid_fertilizer;// 액상비료 살포기
    private String combine;         // 콤바인
    private String tree_crush;      // 나무 분쇄기
    private List<String> picture;   // 사진


    public List<String> getPicture() {
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }

    public String getFluid_fertilizer() {
        return fluid_fertilizer;
    }

    public void setFluid_fertilizer(String fluid_fertilizer) {
        this.fluid_fertilizer = fluid_fertilizer;
    }

    public String getCombine() {
        return combine;
    }

    public void setCombine(String combine) {
        this.combine = combine;
    }

    public String getTree_crush() {
        return tree_crush;
    }

    public void setTree_crush(String tree_crush) {
        this.tree_crush = tree_crush;
    }

    public String getTractor() {
        return tractor;
    }

    public void setTractor(String tractor) {
        this.tractor = tractor;
    }

    public String getRice_planting() {
        return rice_planting;
    }

    public void setRice_planting(String rice_planting) {
        this.rice_planting = rice_planting;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getArea_size() {
        return area_size;
    }

    public void setArea_size(int area_size) {
        this.area_size = area_size;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getCrops() {
        return crops;
    }

    public void setCrops(String crops) {
        this.crops = crops;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
