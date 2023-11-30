package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.bean.Monster;

/**
 * @author 韩顺平
 * @version 1.0
 * 在Mapper接口使用 @Mapper 就会扫描,并将Mapper接口对象注入
 */
@Mapper
public interface MonsterMapper {
    //方法,根据id返回Monster对象
    public Monster getMonsterById(Integer id);
}
