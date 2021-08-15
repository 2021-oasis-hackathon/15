package hackathon.core.common;

import hackathon.core.domain.Member;
import hackathon.core.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginServiceApi {

    private final LoginService loginService;

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public Member MemberInfo(@RequestBody Member member) {
        Member loginMember = loginService.login(member.getLoginId(), member.getPassword());
        if (loginMember == null)
            return null;
        else
            return loginMember;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login/save")
    public String MemberSave(@RequestBody Member member) {

        if (loginService.save(member).getName() == "회원가입실패")
            return "아이디 중복";
        else
            return "회원가입완료";

    }

}
