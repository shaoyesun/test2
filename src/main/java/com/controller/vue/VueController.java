package com.controller.vue;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by root on 16-11-17.
 */
@Controller
@RequestMapping(value = "/vue")
public class VueController {

    @RequestMapping(value = "/vueStudy")
    public String vueStudy(){
        return "vue";
    }

}
