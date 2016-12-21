package com.classTest.classload;

import java.lang.reflect.Field;

/**
 * Created by root on 16-12-21.
 */
public class ObtainField {
    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("com.classTest.classload.OF");
        Field[] fd = clazz.getFields();
        for (Field f : fd) System.out.println(f.getName() + " | " + f.getType());
    }
}

class OF {
    public String name;
    public int age;
    private String address;
}