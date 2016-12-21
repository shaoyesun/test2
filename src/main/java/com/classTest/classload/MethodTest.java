package com.classTest.classload;

import java.lang.reflect.Method;

/**
 * Created by root on 16-12-20.
 */
public class MethodTest {

    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("com.classTest.classload.MT");
        Method[] m = clazz.getMethods();
        for (Method mh : m) {
            System.out.print(mh.getName() + " | " + mh.getDefaultValue() + " | " + mh.getReturnType());
            Class[] c = mh.getParameterTypes();
            System.out.print(" | len = " + c.length + " | ");
            for (Class tv : c) {
                System.out.print("  " + tv.getName());
            }
            System.out.println();
        }

        Method method = clazz.getMethod("mt", String.class);
        method.invoke(clazz.newInstance(), "test method");// 对带有指定参数的指定对象调用由此 Method 对象表示的底层方法
    }

}

class MT {
    public String mt(String str) {
        System.out.println(str);
        return str;
    }
}
