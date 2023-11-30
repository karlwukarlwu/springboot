package org.example.controller;

import org.example.bean.Monster;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 韩顺平
 * @version 1.0
 */
@RestController
public class HiController {

    @Resource
    private Monster monster;

    @RequestMapping("/monster00")
    public Monster monster() {
        return monster;
    }
}
