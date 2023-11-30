package com.hspedu.springboot.mybatisplus.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author 韩顺平
 * @version 1.0
 * 老师说明
 * 1. 如果实体类Monster和表名monster是对应, 就可以映射上,则@TableName可以省略
 * 2. 如果实体类Monster和表名monster_不对应，则需要使用@TableName进行指定
 */
@Data
//@TableName(value = "monster")
public class Monster {
    private Integer id;
    private Integer age;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date birthday;
    private String email;
    private String name;
    private String gender;
    private Double salary;
}
