package com.service;

import com.dao.UserDao;
import com.entity.User;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by root on 16-9-28.
 */
@Service
@Transactional
public class UserService {

    @Inject
    private UserDao userDao;

    public void register(String userName, String password){
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        userDao.save(user);
    }

}
