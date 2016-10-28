package com.controller.timer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * spring定时器测试 Created by root on 16-10-28.
 */
@Controller
public class TimerController {

    /**
     * 通过注解的方式实现定时
     */
    @Scheduled(fixedRate = 1000 * 2)
    public void timeTest() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
        System.out.println("timer : " + format.format(new Date()));
    }

    /**
     * 配置方式实现定时器
     */
    public void timeTest1() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
        System.out.println("timer : " + format.format(new Date()));
    }

}
