package hackerton.core;

import hackerton.core.repository.LandRepository;
import hackerton.core.repository.MemoryLandRepository;
import hackerton.core.service.LandService;
import hackerton.core.service.LandServiceImpl;
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
