package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 韩顺平
 * @version 1.0
 */
//@RestController
//A convenience annotation that is itself annotated with @Controller and @ResponseBody.

@Controller
public class MonsterController {

    //等价的写法
    //@RequestMapping(value = "/monster",method = RequestMethod.GET)
    @GetMapping("/monster")
    @ResponseBody
    public String getMonster() {
        return "GET-查询妖怪";
    }

    //等价写法
    //@RequestMapping(value = "/monster", method = RequestMethod.POST)
    @PostMapping("/monster")
    public String saveMonster() {
        return "POST-添加妖怪";
    }

//    如果前后端不分离的话 看rest.html的隐藏域配置
//    前后端分离不用管这个
    //等价写法
    //@RequestMapping(value = "/monster",method = RequestMethod.PUT)
    @PutMapping("/monster")
    public String putMonster() {
        return "PUT-修改妖怪~~";
    }

    //等价写法
    //@RequestMapping(value = "/monster", method = RequestMethod.DELETE)
    @DeleteMapping("/monster")
    public String delMonster() {
        return "DELETE-删除妖怪";
    }

    @RequestMapping("/go")
    public String go() {
        return "hello"; // 注意 1 看controller有没有 /hello[没有配置视图解析器] 2. 如果配置了视图解析器, 就按照视图解析器来定位页面
    }

}
