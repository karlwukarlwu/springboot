package com.hspedu.hspspringboot.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 韩顺平
 * @version 1.0
 */
@RestController
public class HspHiController {

    @RequestMapping("/hsphi")
    public String hi() {
        return "hi,hsp HspHiController";
    }
}
