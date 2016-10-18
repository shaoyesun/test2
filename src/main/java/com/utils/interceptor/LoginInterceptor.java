package com.utils.interceptor;

import com.entity.User;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("now_user");
        if (session.getAttribute("now_user") == null) {
            response.sendRedirect(request.getContextPath() + "/other/toLogin");
            return false;
        }

        boolean isLogin = false;
        if (user != null) {
            Map<String, String> loginUserMap = (Map<String, String>) session.getServletContext().getAttribute("loginUserMap");
            String sessionId = session.getId();
            for (String key : loginUserMap.keySet()) {
                //用户已在另一处登录
                if (key.equals(user.getUserName()) && !loginUserMap.containsValue(sessionId)) {
                    isLogin = true;
                    break;
                }
            }
        }
        if(isLogin){
            session.setAttribute("message", "用户："+user.getUserName()+"，已在别处登录!");
            response.sendRedirect(request.getContextPath() + "/other/toLogin");
            return false;
        }

        return super.preHandle(request, response, handler);
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
        super.afterCompletion(request, response, handler, ex);
    }
}
