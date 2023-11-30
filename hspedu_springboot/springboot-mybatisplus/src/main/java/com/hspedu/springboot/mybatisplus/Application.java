package com.hspedu.springboot.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 韩顺平
 * @version 1.0
 * 老韩解读
 * 1. 使用@MapperScan 可以指定要扫描的Mapper接口
 * 2. 属性basePackages 可以指定多个包，这里老师指定的是com.hspedu.springboot.mybatisplus.mapper
 */
@MapperScan(basePackages = {"com.hspedu.springboot.mybatisplus.mapper"})
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
