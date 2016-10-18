package com.service.limitelogin;

import com.entity.User;
import com.service.UserService;
import com.utils.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by root on 16-10-16.
 */
@Service
@Transactional
public class LimiteLogin {

    private static Map<String, String> loginUserMap = new HashMap<>();//存储在线用户
    private static Map<String, String> loginOutTime = new HashMap<>();//存储剔除用户时间
    @Autowired
    private UserService userService;

    public String loginLimite(HttpServletRequest request, String userName) {
        User user = userService.findByUserName(userName);
        String sessionId = request.getSession().getId();
        for (String key : loginUserMap.keySet()) {
            //用户已在另一处登录
            if (key.equals(user.getUserName()) && !loginUserMap.containsValue(sessionId)) {
                loginOutTime.put(user.getUserName(), DateUtil.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
                loginUserMap.remove(user.getUserName());
                break;
            }
        }

        loginUserMap.put(user.getUserName(), sessionId);
        request.getSession().getServletContext().setAttribute("loginUserMap", loginUserMap);
        request.getSession().getServletContext().setAttribute("loginOutTime", loginOutTime);
        System.out.println("sessionId = " + loginUserMap.get(userName));
        return "success";
    }


}
