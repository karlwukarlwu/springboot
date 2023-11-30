package com.hspedu.springboot;

import com.hspedu.springboot.bean.Furn;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 韩顺平
 * @version 1.0
 * 这里老师演示一下如何在Springboot开发测试类
 */
@SpringBootTest
public class ApplicationTests {

    //小伙伴回忆一下，在讲解spring时，讲过JdbcTemplate
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test
    public void contextLoads() {

        BeanPropertyRowMapper<Furn> rowMapper =
                new BeanPropertyRowMapper<>(Furn.class);

        List<Furn> furns = jdbcTemplate.query("SELECT * FROM `furn`", rowMapper);
        for (Furn furn : furns) {
            System.out.println(furn);
        }

        //这里老韩再次输出，看看底层使用的是什么数据源类型[HiKariDatasource]
        System.out.println(jdbcTemplate.getDataSource().getClass());
    }
}
