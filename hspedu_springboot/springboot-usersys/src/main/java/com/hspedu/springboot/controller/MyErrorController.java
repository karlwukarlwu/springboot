package com.hspedu.springboot.controller;

import com.hspedu.springboot.exception.AccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Controller
public class MyErrorController {

    //模拟一个服务器内部错误500
    @GetMapping("/err")
    public String err() {
        int i = 10 / 0; //算术异常
        return "manage";
    }

    //这里我们配置的是Post方式请求 /err2
    //老师一会使用 get方式来请求 /err2 , 这样就会出现一个405开头的客户端错误
    @PostMapping("/err2")
    public String err2() {
        //..
        return "manage";
    }

    //编写方法,模拟一个AccessException
    @GetMapping("/err3")
    public String err3(String name) {
        //如果用户不是tom,我们就认为，无权访问-模拟
        if(!"tom".equals(name)) {
            //throw new AccessException();
            throw new AccessException("老韩自定义的AccessException..");
        }
        return "manage";//视图地址, 请求转发
        //return "redirect:/manage.html";
    }
}
