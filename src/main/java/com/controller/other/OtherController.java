package com.controller.other;

import com.entity.User;
import com.service.UserService;
import com.service.token.TokenService;
import com.service.token.UserAuthenticationToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

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
        String result = userService.login(userName, password);

        /*User user =  (User)request.getSession().getAttribute("now_user");
        if(user != null){
            System.out.println("user have existed : " + user.getId() + " | " + user.getUserName());
            return "redirect:/other/toLogin";
        }*/
        String sessionId = request.getSession().getId();
        System.out.println("sessionId = " + sessionId);

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
        redirectAttributes.addFlashAttribute("message", result);
        return "redirect:/other/toLogin";
    }

}
