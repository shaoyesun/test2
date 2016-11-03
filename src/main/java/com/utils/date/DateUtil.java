package com.utils.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 16-8-17.
 */
public class DateUtil {

    private static final String format_10 = "yyyy-MM-dd";
    private static final String format_19 = "yyyy-MM-dd HH:mm:ss";

    /**
     * 根据指定格式获取String类型的Date
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateFormat(Date date, DateFormatEnum format) {
        if (format.toString().equals("format_10")) {
            return new SimpleDateFormat(format_10).format(date);
        } else {
            return new SimpleDateFormat(format_19).format(date);
        }
    }


    public static void main(String arg[]) {
        System.out.println(DateFormatEnum.format_10.toString());
    }
}
