package com.hspedu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 韩顺平
 * @version 1.0
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        /**
         * private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/META-INF/resources/",
         *    "classpath:/resources/", "classpath:/static/", "classpath:/public/" };
         */
        SpringApplication.run(Application.class, args);
    }
}
