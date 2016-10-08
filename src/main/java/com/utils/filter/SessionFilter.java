package com.utils.filter;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by enward on 2015/8/17.
 * 该过滤器主要用来管理当前系统的在线人员，
 * 将session数据存放到application对象中，
 * 用来其他人来操作改用户
 */
public class SessionFilter implements HttpSessionListener {

    /**
     * 如果系统中创建了一个session则将改session存放到application对象中。
     *
     * @param httpSessionEvent 用来获取session对象
     */
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();

        ServletContext application = session.getServletContext();
        Set<HttpSession> sessions = (Set<HttpSession>) application.getAttribute("sessions");
        if (sessions == null) {
            sessions = new HashSet<>();
            application.setAttribute("sessions",sessions);
        }
        sessions.add(session);
    }

    /**
     * 如果系统中销毁了一个session则将队列中的session处理掉
     *
     * @param httpSessionEvent
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        ServletContext application = session.getServletContext();
        Set<HttpSession> sessions = (Set<HttpSession>) application.getAttribute("sessions");
        sessions.remove(session);
    }
}
