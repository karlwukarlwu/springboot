package org.example.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 韩顺平
 * @version 1.0
 */
//有了这个prefix就会去找yml 文件的 monster
// 这个会和application.yml中的 monster 进行绑定
// 会和application.properties中的 monster.xxx冲突 要避免同时命名
@ConfigurationProperties(prefix = "monster")
@Component
@Data
public class Monster {
    private Integer id;
    private String name;
    private Integer age;
    private Boolean isMarried;
    private Date birth;
    private Car car;
    private String[] skill;
    private List<String> hobby;
    private Map<String, Object> wife;
    private Set<Double> salaries;
    private Map<String, List<Car>> cars;
}
