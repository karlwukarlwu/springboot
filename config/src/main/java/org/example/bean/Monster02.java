package org.example.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Data
public class Monster02 {
    private Integer id;
    private String name;
    private Integer age;
    private Boolean isMarried;
    private Date birth;
    private Car car;
}
