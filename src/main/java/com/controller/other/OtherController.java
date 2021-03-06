package com.controller.other;

import com.service.UserService;
import com.service.limitelogin.LimiteLogin;
import com.service.token.TokenService;
import com.service.token.UserAuthenticationToken;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by root on 16-9-28.
 */
@Controller
@RequestMapping(value = "/other")
public class OtherController {

    private static Logger log = Logger.getLogger(OtherController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private LimiteLogin limiteLogin;

    /**
     * 跳转到登录页面
     *
     * @return
     */
    @RequestMapping(value = "/toLogin")
    public String toLogin() {
        return "login";
    }

    /**
     * 登录
     *
     * @param userName
     * @param password
     * @param redirectAttributes
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String userName, String password, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        //判断用户是否已经在线及处理（已在线则剔除）
        String loginLimite = limiteLogin.loginLimite(request, userName);
        log.info("userName : " + userName + " " + loginLimite);
        //判断用户名、密码是否正确
        String result = userService.login(userName, password);
        if (result.equals("success")) {
            request.getSession().setAttribute("now_user", userService.findByUserName(userName));

            //创建token及验证
            String jwtToken = tokenService.createUserAuthToken(userService.findByUserName(userName));//生成token
            System.out.println(jwtToken);
            UserAuthenticationToken authToken = tokenService.retrieveUserAuthToken(jwtToken);//token解析
            System.out.println(authToken.isAuthenticated());
            System.out.println("id = " + UserAuthenticationToken.getCurrentToken().getUserUuid());

            //用户掉线，登录后重定向到保存的链接
            Object url = request.getSession().getAttribute("redirect_link");
            if (url != null) {
                request.getSession().removeAttribute("redirect_link");
                return "redirect:" + url.toString();
            }
            log.info("userName : " + userName + "login success!");
            return "index";
        }
        redirectAttributes.addFlashAttribute("message", result);
        return "redirect:/other/toLogin";
    }

    private void sessionInfo(HttpServletRequest request) {
        java.util.Enumeration e = request.getSession().getAttributeNames();
        while (e.hasMoreElements()) {
            String sessionName = (String) e.nextElement();
            System.out.println("\nsession item name=" + sessionName);
            System.out.println("\nsession item value=" + request.getSession().getAttribute(sessionName));
        }
    }

    /**
     * 多用户登录限制,清除session信息(登录信息、提示信息)
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/clearUserSession")
    public String clearUserSession(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        //httpSession.invalidate();
        httpSession.removeAttribute("now_user");
        httpSession.removeAttribute("mess");
        return "success";
    }

}
