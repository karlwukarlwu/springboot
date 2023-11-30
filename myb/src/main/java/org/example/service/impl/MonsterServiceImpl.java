package org.example.service.impl;

import org.example.bean.Monster;
import org.example.mapper.MonsterMapper;
import org.example.service.MonsterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Service
public class MonsterServiceImpl implements MonsterService {

    //装配MonsterMapper
    @Resource
    private MonsterMapper monsterMapper;

    @Override
    public Monster getMonsterById(Integer id) {
        return monsterMapper.getMonsterById(id);
    }
}
