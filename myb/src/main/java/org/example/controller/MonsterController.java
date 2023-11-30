package org.example.controller;

import org.example.bean.Monster;
import org.example.service.MonsterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Controller
public class MonsterController {

    //装配MonsterService
    @Resource
    private MonsterService monsterService;


    @ResponseBody
    @GetMapping("/monster")
    public Monster getMonsterById(@RequestParam(value = "id") Integer id){

        return monsterService.getMonsterById(id);
    }
}
