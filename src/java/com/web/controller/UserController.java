package com.web.controller;

import com.web.form.ResultSetForm;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.web.global.EnumReturnCode.ReturnCode;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login")
    public ResponseEntity<ResultSetForm> login(@RequestBody Map<String,Object> map) {
        ResultSetForm rsf = new ResultSetForm();
        try {
            // Get param
            String account = String.valueOf(map.get("account"));
            String password = String.valueOf(map.get("password"));

            boolean isSuccess = userService.login(account,password);
            if (isSuccess) {
                rsf.setResult(true, ReturnCode.Success, "ok.");
            } else {
                rsf.setResult(false, ReturnCode.Error_Validate, " 用户名或密码错误！");
            }
        } catch (Exception e) {
            rsf.setResult(false, ReturnCode.Error, "运行时错误！");
        }
        return new ResponseEntity<ResultSetForm>(rsf, HttpStatus.OK);
    }
}
