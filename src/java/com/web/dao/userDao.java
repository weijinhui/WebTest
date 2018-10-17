package com.web.dao;

import com.web.pojo.User;

public class userDao {

    public boolean login(User user) {
        if ("weijinhui".equals(user.getUsername()) && "123456".equals(user.getPassword())) {
            return true;
        } else {
            return false;
        }
    }
}
