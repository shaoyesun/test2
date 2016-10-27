package com.utils.annotation;

import java.util.Vector;

/**
 * 用户行为跟踪管理器
 */
public class LogManage {
    private static Vector<LogModel> vector = new Vector<LogModel>();
    private static LogManage log = null;

    private LogManage() {
    }


    public static LogManage getInstance() {
        if (log == null) {
            log = new LogManage();
        }
        return log;
    }

    public void info(LogModel log) {
        vector.add(log);
    }

    public Vector<LogModel> getLog() {
        Vector<LogModel> v = vector;
        vector = new Vector<LogModel>();
        return v;
    }

}
