package com.controller.user;

import com.entity.User;
import com.service.UserService;
import com.utils.ConfigUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by root on 16-9-28.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static Logger log = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(String userName, String password, Model model) {
        String result = userService.register(userName, password);
        log.info("userName = " + userName + " add success!");
        System.out.println(ConfigUtil.getValue("configFileTest"));
        if (result.equals("success")) {
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

    @RequestMapping(value = "/clearUserSession")
    public String clearUserSession(HttpServletRequest request, String userName) {
        HttpSession httpSession = request.getSession();
        Map<String, String> loginUserMap = (Map<String, String>) httpSession.getServletContext().getAttribute("loginUserMap");
        String s = loginUserMap.get(userName);
        httpSession.removeAttribute(loginUserMap.get(userName));
        //httpSession.invalidate();
        loginUserMap.remove(userName);
        httpSession.getServletContext().setAttribute("loginUserMap", loginUserMap);

        return "redirect:/other/toLogin";
    }

    @ResponseBody
    @RequestMapping(value = "/testFilter")
    public String testFilter() {
        return "success";
    }

    @RequestMapping(value = "/loginOut")
    public String loginOut(HttpServletRequest request) {
        /*SecurityUtils.getSubject().getSession().stop();
        SecurityUtils.getSubject().logout();*/
        HttpSession session1 = request.getSession();
        session1.invalidate();
        return "redirect:/other/toLogin";
    }

    @Autowired
    private LocaleResolver localeResolver;

    @RequestMapping(value = "/changeLocal")
    public String changeLocal(HttpServletRequest request,String locale,HttpServletResponse response){
        User user = (User)request.getSession().getAttribute("now_user");
        String s = user.getUserName();
        Locale l = new Locale(locale);
        localeResolver.setLocale(request, response, l);
        return "index";
    }

}
