package com.controller.other;

import com.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @RequestMapping(value = "/login")
    public String login(String userName, String password, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        String result = userService.login(userName, password);
        if (result.equals("success")) {
            request.getSession().setAttribute("now_user", userService.findByUserName(userName));
            Object url = request.getSession().getAttribute("invite_link");
            if (url != null) {
                request.getSession().removeAttribute("invite_link");
                return "redirect:" + url.toString();
            }
            return "index";
        }
        redirectAttributes.addFlashAttribute("message", result);
        return "redirect:/user/toLogin";
    }

}
