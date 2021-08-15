package hackathon.core.service;

import hackathon.core.domain.Member;

public interface LoginService {
    Member login(String loginId, String password);

    Member save(Member member);
}
