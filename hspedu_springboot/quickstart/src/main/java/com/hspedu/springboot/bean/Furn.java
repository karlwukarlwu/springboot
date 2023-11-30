package com.hspedu.springboot.bean;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Component
@ConfigurationProperties(prefix = "furn01")
//@ToString //在编译时，生成toString, 默认情况下，会生成一个无参构造器
/**
 * 说明:
 * 1. Equivalent to {@code @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode}
 * 2. @Data 注解等价使用了 如下注解 @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode
 * 3. @RequiredArgsConstructor : 单独的说一下:
 */
//@Data
//老师说明: @NoArgsConstructor 在编译时，会生成无参构造器, 前面老师说过，默认情况下，会生成一个无参构造器
//老师说明：当我们有其它构造器生成时，如果你希望仍然有无参构造器就需要使用@NoArgsConstructor指定一下
//        ,否则就会覆盖无参构造器，从而代码错误
@NoArgsConstructor
//老师说明：@AllArgsConstructor 在编译时，会生成全参构造器
@AllArgsConstructor
@ToString
@Setter
@Getter
public class Furn {
    private Integer id;
    private String name;
    private Double price;


}
