package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.bean.Monster;

/**
 * @author 韩顺平
 * @version 1.0
 * baseMapper 来自于mybatis-plus
 *
 * 1. BaseMapper已经默认提供了很多的crud方法，可以直接使用
 * 2. 如果BaseMapper 提供的方法不能满足业务需求，我们可以再开发新的方法，
 *    并在MonsterMapper.xml进行配置 => 使用插件开发
 */
@Mapper
public interface MonsterMapper extends BaseMapper<Monster> {
    //自定义方法
    //增加一个添加方法insert

    int insertSelective(Monster monster);

    int delByEmail(@Param("email") String email);


}
