package com.classTest.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by root on 16-12-1.
 */
public class WriterAndReaderTest {
    public static void main(String[] args) {
        File file = new File("/home/sunjf/a.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            InputStream in=new FileInputStream(file);
            byte[] b=new byte[1024];
            in.read(b);
            in.close();
            System.out.println(new String(b));

            /*char[] c = {'你', '好', '，', '流', '！'};
            FileWriter fw = new FileWriter(file);
            //fw.write(c);//写入单个字符
            //fw.write(c, 0, c.length);//将字符写入数组的某一部分
            String str = "你好，流！";
            fw.write(str, 0, str.length());//写入一部分字符串
            fw.flush();//刷新该流的缓冲

            FileReader fr = new FileReader(file);
            int index = 0;
            System.out.println("判断此流是否已经准备好用于读取 : " + fr.ready());
            while ((index = fr.read()) != -1) {
                System.out.print((char) index);//读取单个字符
                //fr.skip('！');//跳过某些字符
            }
            fw.close();//关闭此流，但要先刷新它
            fr.close();//关闭该流并释放与之关联的所有资源*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
