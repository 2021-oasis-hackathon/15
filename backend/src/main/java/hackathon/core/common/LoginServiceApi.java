package hackathon.core.common;

import hackathon.core.domain.Member;
import hackathon.core.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoginServiceApi {

    private final LoginService loginService;
    /**
     테스트 할때 이용해서 주석처리함
     */
//    @RequestMapping(method = RequestMethod.POST, value = "/login/test")
//    public Member MemberInfo(@RequestBody Member member) {
//        Member loginMember = loginService.login(member.getLoginId(), member.getPassword());
//        if (loginMember == null)
//            return null;
//        else
//            return loginMember;
//    }
//    @RequestMapping(method = RequestMethod.POST, value = "/login/test/save")
//    public Member MemberSave(@RequestBody Member member) {
//        return loginService.save(member);
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value = "/login/test/find/all")
//    public List<Member> findAll() {
//        return loginService.findAll();
//    }
//
//    @GetMapping(value = "/login/test/find/id")
//    public Member findById(@RequestParam long id){
//        return loginService.findById(id);
//    }
//
}
