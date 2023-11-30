package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Hello world!
 *
 */
//@ServletComponentScan(basePackages = "com.hspedu.springboot")
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext ioc =
                SpringApplication.run(Application.class, args);
        //ioc.stop();
        System.out.println("hello");
    }
}