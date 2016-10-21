package com.utils;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class StringUtil {
    private static Logger log = Logger.getLogger(StringUtil.class);

    private static String[] word = new String[]{"Q", "W", "E", "R", "T", "Y", "U","P", "A", "S", "D", "F", "G", "H", "J", "K", "Z", "X", "C", "V", "B", "N", "M"};
    private static Integer[] number = new Integer[]{2, 3, 4, 5, 6, 7, 8, 9};

    /**
     * 获取32位唯一字符串
     *
     * @return
     */
    public static String genUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String temp = str.substring(0, 8) + str.substring(9, 13)
                + str.substring(14, 18) + str.substring(19, 23)
                + str.substring(24);
        return temp;
    }

    /**
     * 获取日期字符串
     * eg:20140926105559
     *
     * @return
     */
    public static String getStringDate() {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date());
    }

    /**
     * 格式化double类型的数字，保留2为小数
     *
     * @param f
     * @return
     */
    public static double formatNum(double f) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.parseDouble(df.format(f));
    }

    /**
     * 获取job任务名
     *
     * @return
     */
    public static String getJobname() {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = "a" + format.format(new Date());
        return date;
    }

    /**
     * 获取不同位数的激活码
     *
     * @param num 获取几位的激活码
     * @return 激活码字符串
     */
    public static String getInvitationCode(int num) {
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        for (int i = 0; i < num; i++) {
            if (r.nextInt(3) == 0) {
                sb.append(number[r.nextInt(8)]);
            } else {
                sb.append(word[r.nextInt(23)]);
            }
        }
        return sb.toString();
    }

    /**
     * 使用正则表达式解析描述中的字符串
     *
     * @param originalString 原始描述
     * @return 解析后的描述
     */
    public static String getParseLog(String originalString, HttpServletRequest req) {
        String newString = originalString;
        Pattern p = Pattern.compile("\\{(.*?)\\}", Pattern.DOTALL);
        Matcher matcher = p.matcher(originalString);
        while (matcher.find()) {
            String param = matcher.group(1);
            String value = req.getParameter(param);
            if (value == null || value.equals("")) {
                continue;
            }
            newString = newString.replace("{" + param + "}", value);
        }
        return newString;
    }

    /**
     * 用于转换字符串的编码 iso-8859-1 转换为 utf-8
     *
     * @param string 要转换的字符
     * @return 字符串
     */
    public static String transformCoding(String string) {
        try {
            string = new String(string.getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {
            log.warn("编码转换异常，异常信息为：" + ExceptionUtil.print(e));
        }
        return string;
    }

    /**
     * 该方法用来解析配置文件
     */
    private static void printDatabaseAnno(String path) {
        String s = IoUtil.readCharacterFile(path);
        String[] ss = s.split("\n");
        JSONObject js1 = new JSONObject();
        for (String string : ss) {
            String[] sss = string.split(",");
            JSONObject js = new JSONObject();
            String name = null;
            for (int i = 0; i < sss.length; i++) {
                switch (i) {
                    case 0:
                        name = sss[i];
                        break;
                    case 1:
                        js.put("nr", sss[i]);
                        break;
                    case 2:
                        js.put("nt", sss[i]);
                        break;
                    case 3:
                        js.put("Kegg", sss[i]);
                        break;
                    case 4:
                        js.put("Cog", sss[i]);
                        break;
                    case 5:
                        js.put("Kog", sss[i]);
                        break;
                    case 6:
                        js.put("Pfam", sss[i]);
                        break;
                    case 7:
                        js.put("Swissprot", sss[i]);
                        break;
                    case 8:
                        js.put("TrEMBL", sss[i]);
                        break;
                    default:
                        break;
                }
            }
            js1.put(name, js);
        }
        System.out.println(js1.toString());
    }

    /**
     * 将模板内容转化为字符串，主要用于js方面
     */
    private static void tempToString(String path) {
        String s = IoUtil.readCharacterFile(path);
        String[] ss = s.split("\n");
        StringBuffer sb = new StringBuffer();
        for (String string : ss) {
            string = string.replaceAll("\"", "\\\\\"");
            string = "+\"" + string + "\"";
            sb.append(string + "\n");
        }
        //FileUtil.writeFile(sb.toString(), "D:/asdf");
    }

    public static void main(String[] args) {
        //System.out.println(genUUID());
        //printDatabaseAnno("D:\\demo_Reseq\\RNA_no_ref\\annotation.csv");
        tempToString("D:/a.a");
    }

}
