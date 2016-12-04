package com.classTest.stream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by root on 16-12-4.
 */
public class DateStreamTest {

    public static void main(String[] args) {
        File file = new File("/home/sunjf/a.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            OutputStream fos = new FileOutputStream(file);
            DataOutputStream dos = new DataOutputStream(fos);
            String str = "java流学习！";
            //dos.writeUTF(str);//以与机器无关方式使用 UTF-8 修改版编码将一个字符串写入基础输出流
            dos.writeLong(123l);//将一个 long 值以 8-byte 值形式写入基础输出流中，先写入高字节
            dos.flush();
            dos.close();
            fos.close();

            InputStream fis = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(fis);
            //System.out.println(dis.readUTF());
            System.out.println(dis.readLong());//读取八个输入字节并返回一个 long 值
            dis.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
