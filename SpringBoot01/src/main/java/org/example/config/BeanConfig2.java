package org.example.config;

import org.example.bean.Cat;
import org.example.bean.Dog;
import org.example.bean.Monster;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Karl Rules!
 * 2023/11/25
 * now File Encoding is UTF-8
 */
//Import注解可以导入其他配置类，这样就可以在这个配置类中获取其他配置类中的bean
// 默认组件名字/id就是对应类型的全类名
@Import({Dog.class, Cat.class})
@Configuration
public class BeanConfig2 {
    @Bean
    public Monster monster02() {
        return new Monster(800, "蚂蚁精", 80, "吃小昆虫");
    }

    @Bean(name = "monster_nmw")
    public Monster monster05() {
        return new Monster(200, "牛魔王", 500, "疯魔拳");
    }
    @Bean
    /**
     这个name = "monster_nmw" 的Bean存在还得在这个conditional前面才行 不然也报错
     * 老师解读
     * 1. @ConditionalOnBean(name = "monster_nmw") 表示
     * 2. 当容器中有一个Bean , 名字是monster_nmw (类型不做约束), 就注入dog01这个Dog bean
     * 3. 如果没有 名字是monster_nmw Bean 就不注入dog01这个Dog bean
     * 4. 还有很多其它的条件约束注解，小伙伴可以自己测试
     *
     * 5. @ConditionalOnMissingBean(name = "monster_nmw") 表示在容器中,
     * 没有 名字/id 为 monster_nmw 才注入dog01这个Bean
     *
     * 6. @ConditionalOnBean(name = "monster_nmw") 也可以放在配置类
     * 表示对该配置类的所有要注入的组件，都进行条件约束.
     *
     */
    @ConditionalOnBean(name = "monster_nmw")
    public Dog dog01() {
        return new Dog();
    }



}
