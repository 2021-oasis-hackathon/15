package hackathon.core.repository;

import hackathon.core.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository {
    Optional<Member> findByLoginId(String loginId);

    Member saveMember(Member member);
}
