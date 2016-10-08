package com.utils;

/**
 * 用于输出异常的详细信息
 */
public class ExceptionUtil {

    public static String print(Exception e) {
        StackTraceElement[] s = e.getStackTrace();
        StringBuffer sb = new StringBuffer(e.getMessage() + "\n");
        for (StackTraceElement stackTraceElement : s) {
            sb.append(stackTraceElement.toString() + "\n");
        }
        return sb.toString();
    }

}
