package com.classTest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by root on 17-2-9.
 */
public class ZipCompressing {

    //http://www.cnblogs.com/uipower/p/5864484.html

    private int k = 1; // 定义递归次数变量

    private void zip(String zipFileName, File inputFile) throws Exception {
        System.out.println("压缩中...");
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        BufferedOutputStream bo = new BufferedOutputStream(out);
        zip(out, inputFile, inputFile.getName(), bo);
        bo.close();        out.close(); // 输出流关闭
        System.out.println("压缩完成");
    }

    private void zip(ZipOutputStream out, File f, String base, BufferedOutputStream bo) throws Exception { // 方法重载
        if (f.isDirectory()){
            File[] fl = f.listFiles();
            if (fl.length == 0){
                out.putNextEntry(new ZipEntry(base + "/"));
                System.out.println(base + "/");
            }
            for (int i = 0; i < fl.length; i++) {
                zip(out, fl[i], base + "/" + fl[i].getName(), bo); // 递归遍历子文件夹
            }
            System.out.println("第" + k + "次递归");
            k++;
        } else {
            out.putNextEntry(new ZipEntry(base)); // 创建zip压缩进入点base
            System.out.println(base);
            FileInputStream in = new FileInputStream(f);
            BufferedInputStream bi = new BufferedInputStream(in);
            int b;
            while ((b = bi.read()) != -1) {
                bo.write(b); // 将字节流写入当前zip目录
            }
            bo.flush();
            bi.close();
            in.close(); // 输入流关闭
        }
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        ZipCompressing book = new ZipCompressing();
        try {
            book.zip("/home/bmk/test.zip",new File("/home/bmk/test"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
