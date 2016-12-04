package com.classTest.generic;


/**
 * Created by root on 16-11-29.
 */
public class GenericTest {
    public static void main(String[] args) {
        Box<Integer> box = new Box<Integer>();
        System.out.println(box.coutObject(1, 2, 3, 4, 5));

        Box<Integer> box1 = new Box<Integer>();
        User u1 = new User();
        u1.setAge(12);
        u1.setName("sun1");
        User u2 = new User();
        u2.setAge(22);
        u2.setName("sun2");
        System.out.println(box.coutObject(u1,u2));
    }

}

