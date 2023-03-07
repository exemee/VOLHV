package graduate.work.volhv.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class MySQLConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.mysql")
    public DataSourceProperties mySQLDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource mySQLDataSource() {
        return mySQLDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

}
