package org.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

/**
 * @author 韩顺平
 * @version 1.0
 * @ControllerAdvice: 使用它可以标识一个全局异常处理器/对象
 * 会注入到spring容器
 */
@Slf4j
@ControllerAdvice//表示这是一个全局异常处理器
public class GlobalExceptionHandler {

    //1、编写方法,处理指定异常, 比如我们处理算术异常和空指针异常, 可以指定多个异常
    //2. 这里要处理的异常，由程序员来指定
    //3. Exception e : 表示异常发生后，传递的异常对象
    //4. Model model: 可以将我们的异常信息，放入model,并传递给显示页面
    //提出一个问题，如何获取到异常发生的方法
//@ExceptionHandler 我们需要处理的异常类型
//    这个方法是自定义的，可以随便写，但是要和@ExceptionHandler一起使用
//    反射调用 这些参数是自动注入的
    @ExceptionHandler({ArithmeticException.class, NullPointerException.class,AccessException.class})
    public String handleAritException(Exception e, Model model, HandlerMethod handlerMethod) {

        log.info("异常信息={}", e.getMessage());
        //这里老师将发生的异常信息放入到model,可以再错误页面取出显示
        model.addAttribute("msg", e.getMessage());
        //得到异常发生的方法是哪个
        log.info("异常发生的方法是={}", handlerMethod.getMethod());
        return "/error/global"; //视图地址
    }
}
