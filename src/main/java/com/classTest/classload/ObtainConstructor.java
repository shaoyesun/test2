package com.classTest.classload;

import java.lang.reflect.Constructor;

/**
 * Created by root on 16-12-21.
 */
public class ObtainConstructor {
    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("com.classTest.classload.OC");
        Constructor[] ct = clazz.getConstructors();
        for(Constructor c : ct) System.out.println(c.getName() + " | " + c.getParameterTypes().length);
    }
}

class OC {
    private String name;

    public OC() {
    }

    public OC(String name) {
        this.name = name;
    }
}
