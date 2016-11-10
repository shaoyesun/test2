package com.controller.jquery;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by root on 16-11-10.
 */
@Controller
@RequestMapping(value = "/jquery")
public class JqueryController {

    @RequestMapping(value = "/jquerySelector")
    public String jquerySelector() {
        return "jquerySelector";
    }

    @RequestMapping(value = "/jqueryHideOrShow")
    public String jqueryHideOrShow() {
        return "jqueryHideOrShow";
    }

    @RequestMapping(value = "/jqueryMethod")
    public String jqueryMethod() {
        return "jqueryMethod";
    }

}
