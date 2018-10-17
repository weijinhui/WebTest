package com.web.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public boolean login (String username,String password){
        if("weijinhui".equals(username)&&"123456".equals(password)){
            return true;
        }else {
            return false;
        }

    }
}
