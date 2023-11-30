package com;

import lombok.extern.slf4j.Slf4j;
import org.example.bean.Furn;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Karl Rules!
 * 2023/11/25
 * now File Encoding is UTF-8
 */
//输出日志的注解
@Slf4j
@Controller
public class HiController {

    @Value("${my.website}")
    private String website;
    @Resource
    private Furn furn;

    @RequestMapping("/hi")
    @ResponseBody
    public String hi() {
        System.out.println(website);
        return "Hi, Spring Boot!";
    }
    @RequestMapping("/furn")
    @ResponseBody
    public Furn furn() {
        log.info("furn-" + furn);
        return furn;
    }
}
