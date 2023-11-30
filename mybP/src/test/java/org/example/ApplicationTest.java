package org.example;

import org.example.bean.Monster;
import org.example.mapper.MonsterMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class ApplicationTest {

    @Resource
    private MonsterMapper monsterMapper;

//    @Resource
//    private MonsterService monsterService;

    @Test
    public void testMonsterMapper() {

        Monster monster = monsterMapper.selectById(2);
        System.out.println("monster--" + monster);
    }

//    @Test
//    public void testMonsterService() {
//        //Monster monster = monsterService.getById(2);
//        //System.out.println("monster==" + monster);
//        List<Monster> list = monsterService.list();
//        for (Monster monster : list) {
//            System.out.println("monster--" + monster);
//        }
//
//    }

}
