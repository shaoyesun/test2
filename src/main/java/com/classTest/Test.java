package com.classTest;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by root on 17-2-21.
 */
public class Test {

    public static void main(String[] args) {
        //保留两位小数
        NumberFormat format = new DecimalFormat("#0.00");
        double d = 3.1415;
        System.out.println(format.format(d));

        //获取本月的最后一天
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.DATE, 1);
        c.roll(Calendar.DATE, -1);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(format1.format(c.getTime()));

        String str = "abcdefg";
        StringBuffer sb = new StringBuffer(str);
        System.out.println("字符串逆序输出：" + sb.reverse());

        int[] num = new int[]{2, 1, 5, 4, 7, 3, 9};
        System.out.println("冒泡排序:");
        num = maopaopaixu(num);
        System.out.print("二分法查找:");
        System.out.println(erFenfa(num, 7));
    }

    //冒泡排序
    public static int[] maopaopaixu(int[] num) {
        int len = num.length;
        int index = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (num[i] > num[j]) {
                    index = num[i];
                    num[i] = num[j];
                    num[j] = index;
                }
            }
            for (int k = 0; k < len; k++) System.out.print(num[k] + " ");
            System.out.println();
        }
        return num;
    }

    //二分法查找
    public static int erFenfa(int[] num, int index) {
        int len = num.length;
        int mid = len / 2;
        if (num[mid] == index) {
            return mid;
        }
        int start = 0;
        int end = len;
        while (start <= end) {
            mid = (end + start) / 2;
            if (index < num[mid]) {
                end = mid - 1;
            } else if (index > num[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }


}
