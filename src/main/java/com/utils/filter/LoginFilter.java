package com.utils.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        System.out.println("session = "+session.getAttribute("now_user"));
        String queryUrl = httpRequest.getQueryString() == null ? "" : ("?" + httpRequest.getQueryString());
        String requestUrl = httpRequest.getServletPath().substring(5) + queryUrl;//httpRequest.getServletPath(),获取链接
        System.out.println("RequestURL = " + httpRequest.getRequestURL());//获取链接
        System.out.println("ServletPath = " + httpRequest.getServletPath());//获取路径
        System.out.println("QueryString = " + httpRequest.getQueryString());//获取参数
        System.out.println("requestUrl = " + requestUrl);
        System.out.println("head AuthType = " + httpRequest.getContextPath());
        //请求连接

        if (session.getAttribute("now_user") == null) {//判断用户是否登陆
            if (session.getAttribute("invite_link") == null) {
                session.setAttribute("invite_link", requestUrl);
            }
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/other/toLogin");
            //chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(requestUrl);
        }
    }

    @Override
    public void destroy() {

    }
}
