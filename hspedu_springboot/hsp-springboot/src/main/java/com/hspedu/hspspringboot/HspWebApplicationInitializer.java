package com.hspedu.hspspringboot;

import com.hspedu.hspspringboot.config.HspConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author 韩顺平
 * @version 1.0
 * Initializer： 初始化器
 */

/**
 * 老师解读
 * 1. 创建我们的Spring 容器
 * 2. 加载/关联Spring容器的配置-按照注解的方式
 * 3. 完成Spring容器配置的bean的创建, 依赖注入
 * 4. 创建前端控制器 DispatcherServlet , 并让其持有Spring容器
 * 5. 当DispatcherServlet 持有容器, 就可以进行分发映射, 请小伙伴回忆我们实现SpringMVC底层机制
 * 6. 这里onStartup 是Tomcat调用, 并把ServletContext 对象传入
 */
public class HspWebApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        System.out.println("startup ....");
        //加载Spring web application configuration => 容器
        //自己 写过 HspSpringApplicationContext
        AnnotationConfigWebApplicationContext ac =
                new AnnotationConfigWebApplicationContext();
        //在ac中注册 HspConfig.class 配置类
        ac.register(HspConfig.class);
        ac.refresh();//完成bean的创建和配置

        //1. 创建注册非常重要的前端控制器 DispatcherServlet
        //2. 让DispatcherServlet 持有容器
        //3. 这样就可以进行映射分发, 回忆一下SpringMvc机制[自己实现过]
        //HspDispatcherServlet
        DispatcherServlet dispatcherServlet = new DispatcherServlet(ac);
        //返回了ServletRegistration.Dynamic对象
        ServletRegistration.Dynamic registration =
                servletContext.addServlet("app", dispatcherServlet);
        //当tomcat启动时，加载 dispatcherServlet
        registration.setLoadOnStartup(1);
        //拦截请求，并进行分发处理
        //这里老师在提示 / 和 /* => 在老师讲解 java web , 自己去看看.
        registration.addMapping("/");

    }
}
