package com.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by root on 16-8-17.
 */
public class DateUtil {

    public final static String FORMAT_19 = "yyyy-MM-dd hh:mm:ss";
    public final static String FORMAT_10 = "yyyy-MM-dd";
    public final static String FORMAT_CN_19 = "yyyy年MM月dd日 hh时mm分ss秒";
    public final static String FORMAT_CN_10 = "yyyy年MM月dd日";

    /**
     * 根据指定格式获取String类型的Date
     */
    public static String dateFormat(Date date, String format) {
        if (format.equals("format_10")) {
            return new SimpleDateFormat(FORMAT_10).format(date);
        } else {
            return new SimpleDateFormat(FORMAT_19).format(date);
        }
    }

    /**
     * 根据日期字符串返回相应的Date
     */
    public static Date getDateFromString(String datastr) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

        try {
            if (datastr.length() == 10) {

                date = dateFormat2.parse(datastr);
            } else if (datastr.length() == 19) {
                date = dateFormat.parse(datastr);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }



    /**
     * 日期转为指定格式
     */
    public static String parsDateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 字符串转换为日期
     */
    public static Date parsStringToDate(String formatDate, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(formatDate);
        } catch (ParseException e) {
            System.out.println("输入日期格式不正确，异常信息：" + e);
        }
        return date;
    }

    /**
     * 获得指定日期的前num个月的日期
     */
    public static Date beforeMonth(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.MONTH, num * -1);
        Date dBefore = calendar.getTime();
        return dBefore;
    }

    private static Calendar dateToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 日期为所在月的第几周
     */
    public static int weekOfMonth(Date date) {
        return dateToCalendar(date).get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * 日期为所在年的第几周
     */
    public static int weekOfYear(Date date) {
        return dateToCalendar(date).get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 日期为所在年的第几天
     */
    public static int dayOfYear(Date date) {
        return dateToCalendar(date).get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 根据一个时间获取该时间为星期几
     */
    public static String dayOfWeek(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        return sdf.format(date);
    }

    /**
     * 计算两个时间之间的天数
     */
    public static int countDaysBetween(Date d1, Date d2) {
        Calendar c1 = dateToCalendar(d1);
        Calendar c2 = dateToCalendar(d2);
        return Math.abs(Math.abs(c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR)) * 365 + (c1.get(Calendar.DAY_OF_YEAR) - c2.get(Calendar.DAY_OF_YEAR)));
    }

    public static void main(String arg[]) {
        System.out.println(DateFormatEnum.format_10.toString());
        String formatDate = DateUtil.parsDateToString(new Date(), DateUtil.FORMAT_CN_19);
        System.out.println("日期转为指定格式 : " + formatDate);
        System.out.println("字符串转换为日期 : " + parsStringToDate(formatDate, DateUtil.FORMAT_CN_19));
        System.out.println("获得指定日期的前num个月的日期 : " + beforeMonth(new Date(), 3));
        System.out.println("日期为所在月的第几周 : " + weekOfMonth(new Date()));
        System.out.println("日期为所在年的第几周 : " + weekOfYear(new Date()));
        System.out.println("日期为所在年的第几天 : " + dayOfYear(new Date()));
        System.out.println("日期是星期几 : " + dayOfWeek(new Date()));
        Calendar c = Calendar.getInstance();
        c.set(2017, 10, 25);
        System.out.println("计算两个时间之间的天数 : " + countDaysBetween(new Date(), c.getTime()));
    }
}
