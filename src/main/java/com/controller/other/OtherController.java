package com.controller.other;

import com.service.UserService;
import com.service.limitelogin.LimiteLogin;
import com.service.token.TokenService;
import com.service.token.UserAuthenticationToken;

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
        sessionInfo(request);//打印session信息
        String result = userService.login(userName, password);
        String loginLimite = limiteLogin.loginLimite(request, userName);
        if (loginLimite.equals("logged")) {
            redirectAttributes.addFlashAttribute("message", loginLimite);
            redirectAttributes.addFlashAttribute("userName", userName);
            return "redirect:/other/toLogin";
        }

        if (result.equals("success")) {
            request.getSession().setAttribute("now_user", userService.findByUserName(userName));

            String jwtToken = tokenService.createUserAuthToken(userService.findByUserName(userName));//生成token
            System.out.println(jwtToken);
            UserAuthenticationToken authToken = tokenService.retrieveUserAuthToken(jwtToken);//token解析
            System.out.println(authToken.isAuthenticated());
            System.out.println("id = " + UserAuthenticationToken.getCurrentToken().getUserUuid());

            Object url = request.getSession().getAttribute("invite_link");
            if (url != null) {
                request.getSession().removeAttribute("invite_link");
                return "redirect:" + url.toString();
            }
            return "index";
        }
        System.out.println("-----------------------------------------------------------------");
        sessionInfo(request);//打印session信息
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
        httpSession.invalidate();
        return "success";
    }

}
