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
        boolean isExist = false;
        String sessionId = request.getSession().getId();
        for (String key : loginUserMap.keySet()) {
            //判断是否已经保存该登录用户的信息或者如果是同一个用户进行重复登录那么允许登录
            if (!key.equals(user.getUserName())) {
                continue;
            }
            isExist = true;
            break;
        }

        if (isExist) {
            return "logged";
        } else {
            loginUserMap.put(user.getUserName(), sessionId);
        }
        request.getSession().getServletContext().setAttribute("loginUserMap", loginUserMap);
        return "success";
    }

}
