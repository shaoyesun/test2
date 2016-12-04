package com.classTest.generic;

import java.util.ArrayList;
import java.util.List;

public class GenericOrdination {
    public static void main(String[] args) {
        Box<String> box = new Box<String>("STR");
        User user = new User();
        user.setName("user");
        user.setAge(12);
        Box<User> box1 = new Box<User>(user);
        System.out.println(box.getClass() == box1.getClass());//true
    }
}

class Box<T> {

    private T data;

    public Box() {

    }

    public Box(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public <T> List<T> coutObject(T... data) {
        List<T> list = new ArrayList<T>();
        for(int i = 0; i < data.length; i++) {
            list.add(data[i]);
        }
        return list;
    }

}

class User {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
