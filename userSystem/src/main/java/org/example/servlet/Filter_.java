package org.example.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 韩顺平
 * @version 1.0
 * 开发Filter 并注入
 *
 * 老韩解读
 * 1. @WebFilter 表示Filter_是一个过滤器，并注入容器
 * 2. urlPatterns = {"/css/*", "/images/*"} 当请求  /css/目录资源或者 /images/目录下资源的时候，会经过该过滤器
 * 3. 老师是直接放行后，在经过拦截器, 拦截器是否拦截要根据拦截器的拦截规则
 */
@Slf4j
//@WebFilter(urlPatterns = {"/css/*", "/images/*"})
public class Filter_ implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("--Filter_ init--");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("--Filter_ doFilter--");
        //为了方便观察过滤器处理的资源，我们输出一个uri
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        log.info("过滤器处理的uri={}", httpServletRequest.getRequestURI());
        //我们直接放行-实际开发中，根据自己的业务来决定如何处理
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info("--Filter_ destroy--");
    }
}
