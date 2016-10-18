package com.service.limitelogin;

import com.entity.User;
import com.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by root on 16-10-16.
 */
@Service
@Transactional
public class LimiteLogin {

    private static Map<String, String> loginUserMap = new HashMap<>();
    @Autowired
    private UserService userService;

    public String loginLimite(HttpServletRequest request, String userName) {
        User user = userService.findByUserName(userName);
        String sessionId = request.getSession().getId();
        for (String key : loginUserMap.keySet()) {
            //用户已在另一处登录
            if (key.equals(user.getUserName()) && !loginUserMap.containsValue(sessionId)) {
                loginUserMap.remove(user.getUserName());
                break;
            }
        }

        loginUserMap.put(user.getUserName(), sessionId);
        request.getSession().getServletContext().setAttribute("loginUserMap", loginUserMap);
        System.out.println("sessionId = " + loginUserMap.get(userName));
        return "success";
    }


}
