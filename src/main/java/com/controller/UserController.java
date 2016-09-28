package com.controller;

import com.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by root on 16-9-28.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public String login(){
        return "success";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(String userName, String password){
        userService.register(userName, password);
        return "success";
    }

}
