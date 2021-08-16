package hackathon.core.service;

import hackathon.core.domain.Member;
import hackathon.core.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class LoginService{
    private final MemberRepository memberRepository;

    /**
     *
     * @return 로그인 실패 null
     */
    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

    /**
        테스트 할때 이용해서 주석처리함
     */
//    public Member findById(Long id){
//        return memberRepository.findById(id);
//    }
//
//    public Member save(Member member){
//        return memberRepository.save(member);
//    }
//
//    public List<Member> findAll(){
//        return memberRepository.findAll();
//    }

}
