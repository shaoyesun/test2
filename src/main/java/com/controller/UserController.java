package com.controller;

import com.service.UserService;
import com.utils.ConfigUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by root on 16-9-28.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static Logger log = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/toLogin")
    public String toLogin() {
        return "login";
    }

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

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(String userName, String password, Model model) {
        String result = userService.register(userName, password);
        log.info("userName = " + userName + " add success!");
        System.out.println(ConfigUtil.getValue("configFileTest"));
        if(result.equals("success")){
            return "success";
        }
        model.addAttribute("message", result);
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/findAll")
    public List findAll() {
        return userService.findAll();
    }

    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public String del(Long id) {
        log.info("id = " + id + " del success!");
        return userService.del(id);
    }

    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(Long id, String userName, String password) {
        log.info("id = " + id + ", userName = " + userName + " edit success!");
        return userService.update(id, userName, password);
    }

    @ResponseBody
    @RequestMapping(value = "/testFilter")
    public String testFilter() {
        return "success";
    }

}
