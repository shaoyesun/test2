package com.utils.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义日志注解类
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 对该条日志信息的详细描述
     *
     * @return
     */
    String desc() default "没有标准描述";

    /**
     * 日志等级
     *
     * @return
     */
    LogLevel level() default LogLevel.INFO;

    /**
     * 该条日志是否存入数据库，默认为true
     *
     * @return
     */
    boolean view() default true;

    /**
     * 用来区分不同的连接,合并相同的连接
     *
     * @return
     */
    String operationDesc() default "没有默认描述";

}
