package com.utils.interceptor;

import com.entity.User;
import com.utils.ExceptionUtil;
import com.utils.StringUtil;
import com.utils.annotation.Log;
import com.utils.annotation.LogLevel;
import com.utils.annotation.LogManage;
import com.utils.annotation.LogModel;

import org.apache.log4j.Logger;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.lang.reflect.Method;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用拦截器对系统进行日志记录并保存到数据库
 */
public class LogInterceptor extends HandlerInterceptorAdapter {

    private static Logger log = Logger.getLogger(LogInterceptor.class);

    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("now_user");
        if (user != null) {
            long beginTime = System.currentTimeMillis();// 1、开始时间
            startTimeThreadLocal.set(beginTime);// 线程绑定变量（该数据只有当前请求的线程可见）
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        User user = (User) request.getSession().getAttribute("now_user");
        if (!(handler instanceof HandlerMethod)) {
            return;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        final Log log = method.getAnnotation(Log.class);
        if (log != null && user != null) {
            String newString = StringUtil.getParseLog(log.desc(), request);
            boolean view = log.view();
            String level = log.level().name();
            String opeDesc = log.operationDesc();
            if (view) {
                long endTime = System.currentTimeMillis();
                long beginTime = startTimeThreadLocal.get();
                long consumeTime = endTime - beginTime;
                String paras = request.getQueryString();
                LogModel ul = new LogModel();
                ul.setOperationDesc(opeDesc);
                ul.setCreateDate(Calendar.getInstance());
                ul.setLoginIp(request.getRemoteAddr());
                ul.setOperation(request.getRequestURI());
                ul.setSpendTime(consumeTime);
                ul.setUserId(user.getId());
                ul.setUserName(user.getUserName());
                ul.setParas(paras);
                ul.setLevel(level);
                ul.setMessage(newString);
                if (ex != null) {
                    ul.setError(ExceptionUtil.print(ex));
                    ul.setStatus(500);
                    ul.setLevel(LogLevel.ERROR.name());
                } else {
                    ul.setStatus(200);
                }
                LogManage.getInstance().info(ul);
            }
        }
    }

}
