package com.utils.annotation;

/**
 * 日志级别枚举类型 用于自定义日志注解系统
 */
public enum LogLevel {
    TRACE("TRACE"),

    DEBUG("DEBUG"),

    INFO("INFO"),

    WARN("WARN"),

    ERROR("ERROR");

    private String value;

    LogLevel(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    public static void main(String[] args) {
        System.out.println(LogLevel.DEBUG.toString());
    }
}
