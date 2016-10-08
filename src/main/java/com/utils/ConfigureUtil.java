package com.utils;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 自定义读取key value 配置文件方法
 * <pre>配置文件的结构必须是
 * key=value
 * #开头的为注释
 * 自动忽略空格和空行</pre>
 */
public class ConfigureUtil {

    private static Map<String, String> map = new HashMap<String, String>();
    private static Logger log = Logger.getLogger(ConfigureUtil.class);

    public ConfigureUtil(String path) {
        init(path);
    }

    /**
     * 将配置文件装载到map对象中
     *
     * @param path
     */
    private void init(String path) {
        try {
            String configString = readCharacterFile(path);
            String[] mapString = configString.split("\n");

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
    public String getValue(String key) {
        return map.get(key);
    }

    /**
     * 得到配置文件的map对象
     *
     * @return
     */
    public Map<String, String> getMap() {
        return map;
    }

    /**
     * 得到配置文件的json字符
     *
     * @return
     */
    public String getMapString() {
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
     * 用来读取特定的配置文件
     * <pre>
     *  忽略空行,忽略空格
     *  #开头的为注释文件
     * </pre>
     *
     * @param path 文件路径
     * @return
     */
    public static String readCharacterFile(String path) {
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
        ConfigureUtil cu = new ConfigureUtil("D:\\Users\\liuzm\\Workspaces\\MyEclipse Professional 2014\\cloud_2.0\\etc\\service.properties");
        System.out.println(cu.getMapString());
    }
}
