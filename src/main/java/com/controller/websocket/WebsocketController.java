package com.controller.websocket;

import com.entity.User;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by root on 16-10-27.
 */
@Controller
@RequestMapping(value = "/websocket")
public class WebsocketController {

    private static Logger log = Logger.getLogger(WebsocketController.class);

    @Bean
    public WebSocketHander systemWebSocketHandler() {
        return new WebSocketHander();
    }

    @RequestMapping("/auditing")
    @ResponseBody
    public String auditing(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("now_user");
        systemWebSocketHandler().sendMessageToUsers(new TextMessage(user.getUserName()));
        return "success";
    }

}
