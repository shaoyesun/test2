package com.classTest.stream;

import java.io.File;

/**
 * Created by root on 16-11-30.
 */
public class FileTest {

    public static void main(String[] args) {

        String ps1 = File.pathSeparator;//与系统有关的路径分隔符(':')，为了方便，它被表示为一个字符串
        char ps2 = File.pathSeparatorChar;//与系统有关的路径分隔符(':' 58)
        String ps3 = File.separator;//与系统有关的默认名称分隔符('/')，为了方便，它被表示为一个字符串
        char ps4 = File.separatorChar;//与系统有关的默认名称分隔符('/' 47)

        File file = new File("/home/sunjf/a.txt");//通过将给定路径名字符串转换为抽象路径名来创建一个新 File 实例
        File f = new File("/home/sunjf");
        File f1 = new File(f, "a.txt");//根据 parent 抽象路径名和 child 路径名字符串创建一个新 File 实例
        File f2 = new File("/home/sunjf", "c.txt");//根据 parent 路径名字符串和 child 路径名字符串创建一个新 File 实例

        System.out.println("测试此抽象路径名表示的文件或目录是否存在 : " + file.exists());
        System.out.println("测试应用程序是否可以读取此抽象路径名表示的文件 : " + file.canRead());
        System.out.println("测试应用程序是否可以修改此抽象路径名表示的文件 : " + file.canWrite());

        System.out.println("返回此抽象路径名父目录的路径名字符串；如果此路径名没有指定父目录，则返回 null : " + file.getParent());
        System.out.println("返回由此抽象路径名表示的文件或目录的名称 : " + file.getName());
        System.out.println("将此抽象路径名转换为一个路径名字符串 : " + file.getPath());

        System.out.println("测试此抽象路径名表示的文件是否是一个目录 : " + file.isDirectory());
        System.out.println("测试此抽象路径名表示的文件是否是一个标准文件 : " + file.isFile());
        System.out.println("测试此抽象路径名指定的文件是否是一个隐藏文件 : " + file.isHidden());
        System.out.println("测试此抽象路径名是否为绝对路径名 : " + file.isAbsolute());

        System.out.println("返回此抽象路径名表示的文件最后一次被修改的时间 : " + file.lastModified());
        System.out.println("返回由此抽象路径名表示的文件的长度 : " + file.length());

        String[] pathStr = f.list();//返回一个字符串数组，这些字符串指定此抽象路径名表示的目录中的文件和目录
        for (String s : pathStr) System.out.println("返回一个字符串数组，这些字符串指定此抽象路径名表示的目录中的文件和目录 " + s);
        File[] fileArr = f.listFiles();
        for (File index : fileArr)
            System.out.println("返回一个字符串数组，这些字符串指定此抽象路径名表示的目录中满足指定过滤器的文件和目录 " + index);

        file.delete();//删除此抽象路径名表示的文件或目录

        file.mkdir();//创建此抽象路径名指定的目录
        //file.mkdirs();//创建此抽象路径名指定的目录，包括所有必需但不存在的父目录

        //if (file.exists()) file.delete();
        /*try {
            file.createNewFile();//当且仅当不存在具有此抽象路径名指定名称的文件时，不可分地创建一个新的空文件
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

}
