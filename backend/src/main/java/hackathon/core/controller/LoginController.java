package hackathon.core.controller;

import hackathon.core.domain.LoginForm;
import hackathon.core.domain.Member;
import hackathon.core.service.LoginService;
import hackathon.core.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

//요청을 json으로 내려주면 되지.
//RestController
@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login") //필요 없음. 프론트에서 처리하는 부분
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    @PostMapping("/login")//프론트에서 post로 보내주면 값을 이 LoginForm으로 맞출거. 아이디, 비밀번호.
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()){
            return "login/loginForm"; //바인딩하다가 오류남. 그냥 아무 값도 주지 말고 다시 그페이지.
        }
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if(loginMember == null) {
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm"; // 아이디 또는 비밀번호가 맞지 않음. 에러코드? 보내주기
        }
        //로그인 성공 처리
        //세션이 있으면 있는 세션, 없으면 신규 세션 생성. 로그인 잘 되고 나서 처리.
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER,loginMember); // 여기서 쿠키값 만들었는데 세션에 저장해둠.
        //쿠키 안보내줌??? 이거 한번 찾아보기. 보내줘야하는데

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) { //세션테이블에서 해당부분 삭제하고 내려보내줌.
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

}
