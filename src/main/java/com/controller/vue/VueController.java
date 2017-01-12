package com.controller.vue;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by root on 16-11-17.
 */
@Controller
@RequestMapping(value = "/vue")
public class VueController {

    @RequestMapping(value = "/vueStudy/{pack}/{view}")
    public String vueStudy(@PathVariable String pack, @PathVariable String view) {
        if (view.equals("0")) return pack;
        return pack + "/" + view;
    }
}
