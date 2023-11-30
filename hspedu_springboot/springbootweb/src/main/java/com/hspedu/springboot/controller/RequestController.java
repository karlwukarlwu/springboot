package com.hspedu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author 韩顺平
 * @version 1.0
 */

@Controller
public class RequestController {

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        request.setAttribute("user", "老韩~");//向request域中添加的数据
        request.getSession().setAttribute("website", "http://www.baidu.com"); //向session中添加数据
        return "forward:/ok";  //请求转发到  /ok
    }

    @GetMapping("/ok")
    @ResponseBody
    public String ok(
            HttpServletRequest request,
            @SessionAttribute(value = "website", required = false) String website,
            @RequestAttribute(value = "user", required = false) String username
    ) {
        //获取到request域中的数据
        System.out.println("username-" + username);
        System.out.println("通过servlet api 获取 username-" + request.getAttribute("user"));
        System.out.println("website-" + website);
        System.out.println("通过servlet api 获取 website-" + request.getSession().getAttribute("website"));
        return "success";
    }

    //响应一个注册请求
    @GetMapping("/register")
    public String register(Map<String,Object> map,
                           Model model,
                           HttpServletResponse response) {
        //如果一个注册请求,会将注册数据封装到map或者model
        //map中的数据和model的数据，会被放入到request域中
        map.put("user","hspedu");
        map.put("job","java架构师");
        model.addAttribute("sal", 80000);
//        会把map和model 放到defaultModel中 然后最后放到request域中
        //一会我们再测试response使用
        //我们演示创建cookie,并通过response 添加到浏览器/客户端
        Cookie cookie = new Cookie("email", "hspedu@sohu.com");
        response.addCookie(cookie);

        //请求转发
        return "forward:/registerOk";

    }

    @ResponseBody
    @GetMapping("/registerOk")
    public String registerOk(HttpServletRequest request) {

        System.out.println("user-" + request.getAttribute("user"));
        System.out.println("job-" + request.getAttribute("job"));
        System.out.println("sal-" + request.getAttribute("sal"));

        return "success";
    }

}
