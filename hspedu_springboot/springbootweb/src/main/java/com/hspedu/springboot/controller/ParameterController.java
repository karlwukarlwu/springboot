package com.hspedu.springboot.controller;

import com.hspedu.springboot.bean.Monster;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author 韩顺平
 * @version 1.0
 */
@RestController
public class ParameterController {
    /**
     * /monster/{id}/{name} 解读
     * 1. /monster/{id}/{name} 构成完整请求路径
     * 2. {id} {name} 就是占位变量
     * 3. @PathVariable("name"): 这里name 和{name} 命名保持一致
     * 4. String name_ 这里自定义，老师故意这样写下
     * 5. @PathVariable Map<String, String> map 把所有传递的值传入map
     * 6. 可以看下@PathVariable源码
     */
    @GetMapping("/monster/{id}/{name}")
    public String pathVariable(@PathVariable("id") Integer id,
                               @PathVariable("name") String name,
                               @PathVariable Map<String, String> map) {
        System.out.println("id-" + id);
        System.out.println("name-" + name);
        System.out.println("map-" + map);
        return "success";
    }

    /**
     * @RequestHeader("Host") 获取http请求头的 host信息
     * @RequestHeader Map<String, String> header: 获取到http请求的所有信息
     */
    @GetMapping("/requestHeader")
    public String requestHeader(@RequestHeader("host") String host,
                                @RequestHeader Map<String, String> header,
                                @RequestHeader("accept") String accept) {
        System.out.println("host-" + host);
        System.out.println("header-" + header);
        System.out.println("accept-" + accept);
        return "success";
    }

    /**
     * 老师解读
     * 如果我们希望将所有的请求参数的值都获取到，可以通过
     *
     * @param username
     * @param fruits
     * @param paras
     * @return
     * @RequestParam Map<String, String> paras 获取
     */
    @GetMapping("/hi")
    public String hi(@RequestParam(value = "name") String username,
                     @RequestParam("fruit") List<String> fruits,
                     @RequestParam Map<String, String> paras) {

        System.out.println("username-" + username);
        System.out.println("fruit-" + fruits);
        System.out.println("paras-" + paras);
        return "success";
    }

    /**
     * 因为我的浏览器目前没有cookie，我们可以自己设置cookie[技巧还是非常有用]
     * 如果要测试，可以先写一个方法，在浏览器创建对应的cookie
     * 说明 1. value = "cookie_key" 表示接收名字为 cookie_key的cookie
     * 2. 如果浏览器携带来对应的cookie , 那么 后面的参数是String ,则接收到的是对应对value
     * 3. 后面的参数是Cookie ,则接收到的是封装好的对应的cookie
     */
    @GetMapping("/cookie")
    public String cookie(@CookieValue(value = "cookie_key", required = false) String cookie_value,
                         HttpServletRequest request,
                         @CookieValue(value = "username", required = false) Cookie cookie) {
        System.out.println("cookie_value-" + cookie_value);
        if (cookie != null) {
            System.out.println("username-" + cookie.getName() + "-" + cookie.getValue());
        }
        System.out.println("-------------------------");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie1 : cookies) {
            System.out.println(cookie1.getName() + "=>" + cookie1.getValue());
        }
        return "success";
    }

    /**
     * @RequestBody 是整体取出Post请求内容
     */
    @PostMapping("/save")
    public String postMethod(@RequestBody String content) {
        System.out.println("content-" + content);
        return "success";
    }


    //处理添加monster的方法

    @PostMapping("/savemonster")
    public String saveMonster(Monster monster) {
        System.out.println("monster-" + monster);
        return "success";
    }

}
