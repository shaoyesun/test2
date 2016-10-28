package com.controller.user;

import com.entity.User;
import com.service.UserService;
import com.utils.ConfigUtil;
import com.utils.annotation.Log;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    /**
     * 添加新的用户
     *
     * @param userName
     * @param password
     * @param model
     * @return
     */
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

    /**
     * 获取所有用户
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findAll")
    public List findAll() {
        return userService.findAll();
    }

    /**
     * 删除指定用户
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public String del(Long id) {
        log.info("id = " + id + " del success!");
        return userService.del(id);
    }

    /**
     * 编辑指定用户信息
     *
     * @param id
     * @param userName
     * @param password
     * @return
     */
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

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginOut")
    public String loginOut(HttpServletRequest request) {
        HttpSession session1 = request.getSession();
        session1.invalidate();
        return "redirect:/other/toLogin";
    }

    @Autowired
    private LocaleResolver localeResolver;

    @Log(desc = "中英文切换")
    @RequestMapping(value = "/changeLocal")
    public String changeLocal(HttpServletRequest request, String locale, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("now_user");
        Locale l = new Locale(locale);
        localeResolver.setLocale(request, response, l);
        return "index";
    }

}
