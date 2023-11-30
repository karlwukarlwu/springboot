package org.example.config;

import org.example.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Configuration
//名称是自己起的，不是固定的
public class WebConfig /*implements WebMvcConfigurer*/ {
//    WebMvcConfigurer 这个接口中有很多方法，我们只需要重写我们需要的方法即可 有跨域的，有拦截器的，有转换器的。。。

//    两种写法 1. implements WebMvcConfigurer 然后重写方法
//            2. @Bean
    //@Override
    //public void addInterceptors(InterceptorRegistry registry) {
    //
    //    //注册自定义拦截器LoginInterceptor
    //    registry.addInterceptor(new LoginInterceptor())
    //            .addPathPatterns("/**") //拦截所有的请求，可以放多个参数
    //            .excludePathPatterns("/","/login","/images/**");//指定要放行的，后面可以根据业务需求，来添加放行的请求路径
    //}

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {

        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                System.out.println("addInterceptors~~~");
                //注册拦截器
                registry.addInterceptor(new LoginInterceptor())
                        .addPathPatterns("/**")//请求图片 css js是新的http请求，所以要放行
                        .excludePathPatterns("/","/login","/images/**","/upload.html","/upload","/sql");
//                为什么不写static 这些文件夹了 因为springboot内部已经配置了这些静态资源文件夹
            }
        };
    }
}
