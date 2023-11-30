package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Karl Rules!
 * 2023/11/25
 * now File Encoding is UTF-8
 */
@Controller
public class HelloController {
 @RequestMapping("/hello")
 @ResponseBody //表示返回的是字符串，而不是页面
     public String hello() {
         return "Hello, Spring Boot!";
     }
}
