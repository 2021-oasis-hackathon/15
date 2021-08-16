package hackathon.core.domain;


import lombok.Data;


@Data
public class Member {
    private Long id;

    private String loginId;//로그인아이디

    private String name;//사용자이름

    private String password;

    private String phoneNumber;

}



