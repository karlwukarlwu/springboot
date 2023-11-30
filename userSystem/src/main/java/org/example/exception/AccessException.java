package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author 韩顺平
 * @version 1.0
 * AccessException : 我们自定义的一个异常
 * 关于自定义异常, 我们在java基础中是讲解过 => 融合贯通
 * value = HttpStatus.FORBIDDEN: 表示发生AccessException异常，我们通过http协议返回的状态码 403
 * 这个状态码和自定义异常的对应关系是由程序员来决定[尽量合理来设置]
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AccessException extends RuntimeException {

    //老韩提供一个构造器,可以指定信息
    public AccessException(String message) {
        super(message);
    }

    //显示的定义一下无参构造器

    public AccessException() {
    }
}
