package com.classTest.classload;

import java.lang.reflect.Field;

/**
 * Created by root on 16-12-20.
 */
public class FieldTest {

    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("com.classTest.classload.FT");
        Field[] f = clazz.getFields();
        for(Field fd : f) {
            System.out.println(fd.getName() + " | " + fd.getType() + " | " + fd.getModifiers() + " | " + fd.get(""));
        }

    }

}

class FT{
    public static String name = "name";
    public static int age = 11;
}
