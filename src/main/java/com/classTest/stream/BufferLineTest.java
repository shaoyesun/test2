package com.classTest.stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * Created by root on 16-12-4.
 */
public class BufferLineTest {

    public static void main(String[] args) {
        File file = new File("D:/a.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Writer fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);//需要通过Writer指定连接的I/O流
            String str = "你好，流！";
            bw.write(str);
            bw.newLine();//写入一个行分隔符
            bw.write(str);
            bw.newLine();
            bw.write(str);
            bw.newLine();
            bw.flush();

            Reader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String s = "";
            while ((s = br.readLine()) != null) System.out.println(s);//读取一个文本行
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
