package com.hspedu.springboot.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author 韩顺平
 * @version 1.0
 * 老韩建议, 最好是先自己把这个类写出,然后拷贝自己需要的代码, 这样自己写代码也知道是为什么
 *
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
