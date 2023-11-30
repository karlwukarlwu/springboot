package org.example.config;

import org.example.bean.Monster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Karl Rules!
 * 2023/11/25
 * now File Encoding is UTF-8
 */
//表示这是一个配置类，相当于一个xml文件
//    我们也可以用BeanConfig 来代理获取bean

/**
 *  * 1. proxyBeanMethods：代理bean的方法
 *  * (1) Full(proxyBeanMethods = true)、【保证每个@Bean方法被调用多少次返回的组件都是单实例的, 是代理方式】
 *  * (2) Lite(proxyBeanMethods = false)【每个@Bean方法被调用多少次返回的组件都是新创建的, 是非代理方式】
 *  * (3) 特别说明: proxyBeanMethods 是在 调用@Bean方法 才生效，因此，需要先获取BeanConfig 组件，再调用方法
 *  * 而不是直接通过 SpringBoot 主程序得到的容器来获取bean, 注意观察直接通过ioc.getBean() 获取Bean, proxyBeanMethods 值并没有生效
 *  * (4) 如何选择: 组件依赖必须使用Full模式默认。如果不需要组件依赖使用 Lite模
 *  * (5) Lite模 也称为轻量级模式，因为不检测依赖关系，运行速度快
 */
//@Configuration(proxyBeanMethods = false)//这样默认所有方法返回的都是单例的
@Configuration//代理默认是true
public class BeanConfig {
    /**
     * @Bean注解相当于<bean>标签 就是Monster Bean
     * Monster03 相当于id
     * new Monster(200, "牛魔王", 200, "金箍棒") 相当于具体注入的bean信息
     * 默认是单实例的
     * 通过 @Scope("prototype")  可以每次返回新的对象，就多例
     * @return
     */
    @Bean//(name="Monster033") 不指定是方法名 指定是name
//    @Scope("prototype")
    public Monster monster03() {
        return new Monster(200, "牛魔王", 200, "金箍棒");
    }
}
