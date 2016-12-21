package com.classTest.classload;

import java.lang.reflect.Method;

/**
 * Created by root on 16-12-21.
 */
public class ObtainMethod {
    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("com.classTest.classload.OM");
        Method[] mt = clazz.getMethods();
        for (Method m : mt)
            System.out.println(m.getName() + " | " + m.getReturnType() + " | " + m.getParameterTypes().length);
    }
}

class OM {
    public void getList() {
    }

    public void getList(String keyword) {
    }
}
