package com.hspedu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Controller
public class HelloController {

    //返回hello,springboot
    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello~, SpringBoot";
    }
}
