package hackathon.core.service;

import hackathon.core.domain.Member;
import hackathon.core.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) { //http post로 아이디, 비밀번호 받고 실행.
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

    @Override
    public Member save(Member member) {
        return memberRepository.saveMember(member);
    }
}
