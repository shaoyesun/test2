package com.utils;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;

import org.apache.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class IoUtil {

    private static Logger log = Logger.getLogger(IoUtil.class);

    /**
     * 读取二进制文件
     *
     * @param path
     * @return
     */
    public static InputStream readBinaryFile(String path) {
        File file = new File(path);
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            log.warn("" + ExceptionUtil.print(e));
        } finally {
            closeStream(dis);
        }
        return dis;
    }

    /**
     * 读取字符文件
     *
     * @param path
     * @return
     */
    public static String readCharacterFile(String path) {
        StringBuffer sb = new StringBuffer();
        String charset = juChar(path);
        BufferedReader br = null;
        try {
            if (charset == null) {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(path)),5*1024*1024);
            } else {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(path), charset),5*1024*1024);
            }
            String s = br.readLine();
            while (s != null) {
                sb.append(s);
                sb.append("\n");
                s = br.readLine();
            }
        } catch (Exception e) {
            log.warn("" + ExceptionUtil.print(e));
        } finally {
            closeStream(br);
        }
        return sb.toString();
    }

    /**
     * 读取字符文件
     *
     * @param path
     * @return
     */
    public static String readCharacterFile_100(String path) {
        StringBuffer sb = new StringBuffer();
        String charset = juChar(path);
        BufferedReader br = null;
        try {
            if (charset == null) {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            } else {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(path), charset));
            }
            int i = 0;
            String s = br.readLine();
            while (s != null&&i<=100) {
                sb.append(s);
                sb.append("\n");
                s = br.readLine();
                i++;
            }
        } catch (Exception e) {
            log.warn("" + ExceptionUtil.print(e));
        } finally {
            closeStream(br);
        }
        return sb.toString();
    }

    /**
     * 读取缓存读取byte文件
     *
     * @param path
     * @return
     */
    public static String readStreamBufferFile(String path) {
        String str = "";
        File file=new File(path);
        BufferedReader reader=null;
        try{
            reader=new BufferedReader(new FileReader(file),5*1024*1024);   //如果是读大文件  则  new BufferedReader(new FileReader(file),5*1024*1024);  即，设置缓存
            String tempString=null;
            while((tempString=reader.readLine())!=null)
            {
                str += tempString;
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(reader!=null)
            {
                try{
                    reader.close();
                }catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return str;
    }
    /**
     * 预读文件前n行数据
     *
     * @param path
     * @return
     */
    public static String readCharacterFilePreview(String path, Long n) {
        StringBuffer sb = new StringBuffer();
        File file = new File(path);
        String charset = juChar(file.getPath());
        BufferedReader br = null;
        try {
            if (charset == null) {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            } else {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
            }
            String s = br.readLine();
            for (int i = 0; i < n; i++) {
                sb.append(s == null ? "" : s + "\n");
                s = br.readLine();
            }
        } catch (Exception e) {
            log.warn("" + ExceptionUtil.print(e));
        } finally {
            closeStream(br);
        }
        return sb.toString();
    }


    /**
     * 判断文件编码
     *
     * @param filepath
     * @return
     */
    public static String juChar(String filepath) {

        CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
        detector.add(new ParsingDetector(true));

        detector.add(JChardetFacade.getInstance());
        detector.add(ASCIIDetector.getInstance());
        detector.add(UnicodeDetector.getInstance());

        Charset charset = null;

        File file = new File(filepath);
        log.info(file.length());
        InputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream(file));
            charset = detector.detectCodepage(is, 10000);
        } catch (Exception e) {
            log.warn(":" + ExceptionUtil.print(e));
        } finally {
            closeStream(is);
        }

        if (charset != null) {
            log.debug(file.getName() + "Encode" + charset.name());
            return charset.name();
        } else {
            log.debug(file.getName() + "unknow");
            return null;
        }
    }

    /**
     * 获取文件中离该指针位置最近的换行符或者结束符
     *
     * @param l    文件指针
     * @param path 文件路径
     * @return 文件指针
     */
    public static long getNearPointer(long l, String path) {
        RandomAccessFile rf = null;
        try {
            rf = new RandomAccessFile(new File(path), "r");
            rf.seek(l);
            int c = -1;
            while (true) {
                c = rf.read();
                if (c == '\n' || c == '\r') {
                    return rf.getFilePointer();
                }
                if (c == -1) {
                    return l;
                }
                l++;
                rf.seek(l);
            }
        } catch (Exception e) {
            log.warn("读取文件头10kb内容出现异常，异常信息为：" + ExceptionUtil.print(e));
            e.printStackTrace();
        } finally {
            try {
                rf.close();
            } catch (IOException e) {
                log.warn("关闭文件流出现异常，异常信息为：" + ExceptionUtil.print(e));
            }
        }
        return l; //返回l表示文件改方法出错了
    }

    /**
     * 合并多个venn图文件
     *
     * @files 多个文件名用，分割
     */
    public static String mergeVennFile(String[] files,String filePath) throws Exception {
        FileChannel outChannel = null;
        try {
            outChannel = new FileOutputStream(filePath).getChannel();
            for(String f : files){
                FileChannel fc = new FileInputStream(f).getChannel();
                ByteBuffer bb = ByteBuffer.allocate(1024);
                while(fc.read(bb) != -1){
                    bb.flip();
                    outChannel.write(bb);
                    bb.clear();
                }
                fc.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {if (outChannel != null) {outChannel.close();}} catch (IOException ignore) {}
        }
        return filePath;
    }

    /**
     * 以下两个方法用于关闭流
     *
     * @param is
     */
    public static void closeStream(InputStream is) {
        try {
            if (is != null) {
                is.close();
            }
        } catch (IOException e) {
            log.warn("关闭流出现异常，异常信息为：" + ExceptionUtil.print(e));
        }
    }

    public static void closeStream(OutputStream os) {
        try {
            if (os != null) {
                os.close();
            }
        } catch (IOException e) {
            log.warn("关闭流出现异常，异常信息为：" + ExceptionUtil.print(e));
        }
    }

    public static void closeStream(BufferedReader br) {
        try {
            if (br != null) {
                br.close();
            }
        } catch (IOException e) {
            log.warn("关闭流出现异常，异常信息为：" + ExceptionUtil.print(e));
        }
    }

    public static void closeStream(BufferedWriter bw) {
        try {
            if (bw != null) {
                bw.close();
            }
        } catch (IOException e) {
            log.warn("关闭流出现异常，异常信息为：" + ExceptionUtil.print(e));
        }
    }

}
