package org.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Karl Rules!
 * 2023/11/29
 * now File Encoding is UTF-8
 */
@Configuration
public class DruidDataSourceConfig {


    @ConfigurationProperties("spring.datasource")
    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource druidDataSource =
                new DruidDataSource();
        return druidDataSource;
    }
}

