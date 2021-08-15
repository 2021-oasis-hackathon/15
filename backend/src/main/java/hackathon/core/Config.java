package hackathon.core;

import hackathon.core.repository.LandRepository;
import hackathon.core.repository.MemberRepository;
import hackathon.core.repository.MemoryMemberRepository;
import hackathon.core.repository.MemoryLandRepository;
import hackathon.core.service.LandService;
import hackathon.core.service.LandServiceImpl;
import hackathon.core.service.LoginService;
import hackathon.core.service.LoginServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@Configuration
public class Config {

    private DataSource dataSource;

    public Config(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public LandRepository landRepository(){
        return new MemoryLandRepository(dataSource);
    }

    @Bean
    public LandService landService() {
        return new LandServiceImpl(landRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository(dataSource);
    }

    @Bean
    public LoginService loginService(){
        return new LoginServiceImpl(memberRepository());
    }
}
