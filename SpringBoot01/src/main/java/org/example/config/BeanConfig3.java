package org.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Configuration
//导入beans.xml - 就可以获取到beans.xml 中配置bean
@ImportResource(locations = {"classpath:beans.xml"})//导入多个就是{classpath:a,classpath:b,classpath:c....}
public class BeanConfig3 {

}

