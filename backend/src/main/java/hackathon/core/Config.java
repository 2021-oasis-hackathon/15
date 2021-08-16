package hackathon.core;

import hackathon.core.repository.LandRepository;
import hackathon.core.repository.MemberRepository;
import hackathon.core.repository.MemoryMemberRepository;
import hackathon.core.repository.MemoryLandRepository;
import hackathon.core.service.*;
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
        return new LandServiceImpl(landRepository(), coordinateConversionService());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository(dataSource);
    }

    @Bean
    public LoginService loginService(){
        return new LoginServiceImpl(memberRepository());
    }

    @Bean
    public CoordinateConversionService coordinateConversionService(){
        return new CoordinateConversionService();
    }
}
