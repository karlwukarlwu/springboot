package org.example.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Slf4j
//@WebListener
public class Listener_ implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        //这里可以加入项目初始化的相关业务代码
        log.info("Listener_ contextInitialized 项目初始化OK~");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //这里可以加入相应代码...
        log.info("Listener_ contextDestroyed 项目销毁");
    }
}
