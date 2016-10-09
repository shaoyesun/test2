package com.controller.user;

import com.service.UserService;
import com.utils.ConfigUtil;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.List;
import java.util.Locale;

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

    private SessionLocaleResolver sessionLocaleResolver;

    @RequestMapping("language")
    public ModelAndView language(HttpServletRequest request, HttpServletResponse response, String language){

        language=language.toLowerCase();
        if(language==null||language.equals("")){
            return new ModelAndView("redirect:/");
        }else{
            if(language.equals("zh_cn")){
                sessionLocaleResolver.setLocale(request, response, Locale.CHINA );
            }else if(language.equals("en")){
                sessionLocaleResolver.setLocale(request, response, Locale.ENGLISH );
            }else{
                sessionLocaleResolver.setLocale(request, response, Locale.CHINA );
            }
        }

        return new ModelAndView("redirect:/");
    }

}
