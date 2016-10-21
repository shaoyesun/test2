package com.controller.log;

import com.service.log.LogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by root on 16-10-21.
 */
@Controller
@RequestMapping(value = "/log")
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping(value = "/saveLog")
    public String saveLog(){
        logService.save();
        return "success";
    }

}
