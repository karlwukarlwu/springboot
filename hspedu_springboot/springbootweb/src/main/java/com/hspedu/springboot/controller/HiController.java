package com.hspedu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 韩顺平
 * @version 1.0
 */
@RestController
public class HiController {

    //@RequestMapping("/1.jpg") //模拟请求的路径和资源的名字冲突
    //@RequestMapping("/hi")
    @RequestMapping("/hellox")
    public String hi() {
        return "hi:):)";
    }
}
