package com.utils;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 该文件专门用来读取service.properties配置文件
 */
public class ConfigUtil {

    private static Map<String, String> map = null;
    private static Long oldModifyDate = 0l;
    private static String path;
    private static Logger log = Logger.getLogger(ConfigUtil.class);

    /**
     * 将配置文件装载到map对象中
     */
    private static void init() {
        URL url = ConfigUtil.class.getResource("/");
        path = url.getFile() + "config/service.properties";
        oldModifyDate = new File(path).lastModified();
        try {
            String configString = readCharacterFile(path);
            String[] mapString = configString.split("\n");
            map = new HashMap<String, String>();
            for (String string : mapString) {
                String[] key_value = string.split("=");
                map.put(key_value[0], key_value[1]);
            }
        } catch (Exception e) {
            log.warn(ExceptionUtil.print(e));
        }
    }

    /**
     * 根据key 获取value值
     *
     * @param key
     * @return
     */
    public static String getValue(String key) {
        judgeIfModify();
        return map.get(key);
    }

    /**
     * 得到配置文件的map对象
     *
     * @return
     */
    public static Map<String, String> getMap() {
        judgeIfModify();
        return map;
    }

    /**
     * 得到配置文件的json字符
     *
     * @return
     */
    public static String getMapString() {
        judgeIfModify();
        Iterator<String> it = map.keySet().iterator();
        JSONObject js = new JSONObject();
        while (it.hasNext()) {
            String key = it.next();
            String value = map.get(key);
            js.put(key, value);
        }
        return js.toString();
    }

    /**
     * 判断配置文件是否有修改
     *
     * @return true为修改了 false为没有修改
     */
    private static boolean judgeIfModify() {
        if (map == null || FileUtils.isFileNewer(new File(path), oldModifyDate)) {
            log.info("service.properties配置文件已经被修改，重新载入该文件，时间为：" + new Date().toString());
            init();
            return true;
        }
        return false;
    }


    /**
     * 用来读取特定的配置文件
     * <pre>
     *  忽略空行,忽略空格
     *  #开头的为注释文件
     * </pre>
     *
     * @param path 文件路径
     * @return
     */
    private static String readCharacterFile(String path) {
        StringBuffer sb = new StringBuffer();
        String charset = IoUtil.juChar(path);
        BufferedReader br = null;
        try {
            if (charset == null) {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            } else {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(path), charset));
            }
            String s = br.readLine();
            while (s != null) {
                s = s.trim();
                if (s.startsWith("#") || s.equals("")) {

                } else {
                    sb.append(s);
                    sb.append("\n");
                }
                s = br.readLine();
            }
        } catch (Exception e) {
            log.warn("" + ExceptionUtil.print(e));
        } finally {
            IoUtil.closeStream(br);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(ConfigUtil.getMapString());
        System.out.println(oldModifyDate);
    }

}
