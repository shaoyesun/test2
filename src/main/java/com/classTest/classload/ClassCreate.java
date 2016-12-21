package com.classTest.classload;

/**
 * Created by root on 16-12-21.
 */
public class ClassCreate {
    public static void main(String[] args) {
        try {
            Class c1 = Class.forName("java.lang.String");
            String s = new String("string");
            Class c2 = s.getClass();
            Class c3 = String.class;
            System.out.println(c1 + " | " + c2 + " | " + c3);
            System.out.println(c1 == c2);
            System.out.println(c1 == c3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
