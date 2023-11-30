package org.example;

import org.example.bean.A;
import org.example.bean.Cat;
import org.example.bean.Dog;
import org.example.bean.Monster;
import org.example.config.BeanConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
//表示这是一个Spring Boot应用
//    现在扫描路径就更改了，不再是默认的org.example，而是com
//@SpringBootApplication(scanBasePackages = "com") {"com", "org.example"} 扫描多个包的写法
@SpringBootApplication(scanBasePackages = {"com", "org.example"})
public class MainApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext ioc = SpringApplication.run(MainApp.class, args);
//获取容器中所有组件的名字
//        String[] names = ioc.getBeanDefinitionNames();
//        for (String name : names) {
//            System.out.println(name);
//        }

//        A bean = ioc.getBean(A.class);
//        System.out.println("bean = " + bean);

//        传统的spring中用配置xml的方式，注入bean
//        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//        Monster monster03 = context.getBean("monster03", Monster.class);
//        System.out.println("monster03 = " + monster03);

//        Monster monster01 = ioc.getBean( Monster.class);
//        Monster monster02 = ioc.getBean(Monster.class);
//        System.out.println("monster01 = " + monster01.hashCode());
//        System.out.println("monster02 = " + monster02.hashCode());

//        有了@Configuration的注解 本身也是一个bean
//        BeanConfig bean = ioc.getBean(BeanConfig.class);
//        Monster monster = bean.monster03();
//        System.out.println("monster = " + monster);
//        System.out.println("bean = " + bean);

//        当@Configuration(proxyBeanMethods = false)//这样默认所有方法返回的都是单例的
//        这样配置的时候 需要先拿到BeanConfig这个组件，再调用方法，才能保证每次返回的都是新的对象
//        BeanConfig bean = ioc.getBean(BeanConfig.class);
//        Monster monster = bean.monster03();
//        Monster monster1 = bean.monster03();
//        System.out.println("monster = " + monster.hashCode());
//        System.out.println("monster1 = " + monster1.hashCode());

//        当@Configuration(proxyBeanMethods = true)//这样默认所有方法返回的都是单例的
//        这种情况下就不用先拿到BeanConfig这个组件，
//        Monster monster = ioc.getBean("Monster033",Monster.class);
//        Monster monster1 = ioc.getBean("Monster033",Monster.class);
//        System.out.println("monster = " + monster.hashCode());
//        System.out.println("monster1 = " + monster1.hashCode());

//        可以有多个配置类 这些配置类里面的bean实际上都在同一个容器中
//        Monster monster03 = ioc.getBean("monster03", Monster.class);
//        Monster monster02 = ioc.getBean("monster02", Monster.class);
//        System.out.println("monster03--" + monster03);
//        System.out.println("monster02--" + monster02);

        //===测试@Import 使用 start
//      名称就是全类名
//        Dog dogBean = ioc.getBean(Dog.class);
//        Cat catBean = ioc.getBean(Cat.class);
//        System.out.println("dogBean--" + dogBean);
//        System.out.println("catBean--" + catBean);

//        Dog dog01 = ioc.getBean("dog01", Dog.class);
//        System.out.println("dog01--" + dog01);

        //演示@ImportResource 使用 start===

        Monster monster099 = ioc.getBean("monster099", Monster.class);
        System.out.println("monster04-" + monster099);

        System.out.println("monster04 bean 是否存在-" + ioc.containsBean("monster099"));
    }

}
