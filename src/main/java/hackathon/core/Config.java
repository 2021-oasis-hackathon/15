package hackathon.core;

import hackathon.core.repository.LandRepository;
import hackathon.core.repository.MemoryLandRepository;
import hackathon.core.service.LandService;
import hackathon.core.service.LandServiceImpl;
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
}
