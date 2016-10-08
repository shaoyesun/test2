package com.controller;

import com.service.UserService;
import com.utils.ServiceConfigUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by root on 16-9-28.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static Logger log = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public String login() {
        return "success";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(String userName, String password) {
        userService.register(userName, password);
        log.info("userName = " + userName + " add success!");
        System.out.println(ServiceConfigUtil.getValue("configFileTest"));
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
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

}
