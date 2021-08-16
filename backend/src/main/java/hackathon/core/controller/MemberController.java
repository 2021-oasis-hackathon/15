package hackathon.core.controller;

import hackathon.core.domain.Member;
import hackathon.core.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
// return 을 다 바꿔주기 @RestController 방식 쓸거

@Controller

@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/add") //프론트에서 해줄거. 필요없음.
    public String addForm(@ModelAttribute("member") Member member) {

        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute Member member, BindingResult result) {

        if (result.hasErrors()) { // 에러가 떴다고 보내줌.
            return "members/addMemberForm";
        }

        if (duplicateId(member)) { //아이디가 중복되었다고 보내줌.
            System.out.println("아이디 중복.");
            return "members/addMemberForm";
        }

        memberRepository.save(member);
        return "redirect:/"; // 아이디가 저장되었다는 정보 보내줌.


    }

    private boolean duplicateId(Member member) {
        Member hasMember = memberRepository.findAll().stream()
                .filter(m -> m.getLoginId().equals(member.getLoginId()))
                .findAny()
                .orElse(null);
        if (hasMember == null) {
            return false;
        }
        return true;

    }


}

