package com.classTest.stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Created by root on 17-2-14.
 */
public class ByteAndCharTest {
    public static void main(String[] args) throws Exception {
        String str = "中国人";
        //FileOutputStream fos = new FileOutputStream("/home/bmk/test.txt",true);
        /*FileOutputStream fos = new FileOutputStream("/home/bmk/test.txt");
        fos.write(str.getBytes("UTF-8"));
        fos.close();*/

       /* FileWriter fw = new FileWriter("/home/bmk/test.txt");
        fw.write(str);
        fw.close();*/

        /*PrintWriter pw = new PrintWriter("/home/bmk/test.txt", "UTF-8");
        pw.write(str);
        pw.close();*/

        /*FileReader fr = new FileReader("/home/bmk/test.txt");
        char[] buf = new char[1024];
        int len = fr.read(buf);
        String myStr = new String(buf,0,len);
        System.out.println(myStr);*/

        /*FileInputStream fr = new FileInputStream("/home/bmk/test1.txt");
        byte[] buf = new byte[1024];
        int len = fr.read(buf);
        String myStr = new String(buf,0,len,"UTF-8");
        System.out.println(myStr);*/

        /**
         * 按行读取
         */
        /*BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("/home/bmk/test.sh"),"UTF-8"));
        String myStr = "";
        while((myStr = br.readLine()) != null) {
            System.out.println(myStr);
        }
        br.close();*/

        /**
         * 按行读取写入
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("/home/bmk/test.sh"), "UTF-8"));
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/home/bmk/test.txt", true), "UTF-8"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/home/bmk/test.txt"), "UTF-8"));
        String myStr = "";
        while ((myStr = br.readLine()) != null) {
            bw.write(myStr);
            bw.newLine();
            System.out.println(myStr);
        }
        br.close();
        bw.close();
    }

}
