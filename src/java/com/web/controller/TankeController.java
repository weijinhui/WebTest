package com.web.controller;/**
 * @Auther: Administrator
 * @Date: 2019/1/8 0008 17:34
 * @Description:
 */

import com.web.form.ResultSetForm;
import com.web.global.EnumReturnCode;
import com.web.service.TankeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @program: WebTest
 *
 * @description:
 *
 * @author: Carson Wei
 *
 * @create: 2019-01-08 17:34
 **/
@Controller
@RequestMapping(value = "/tanke")
public class TankeController {
    @Autowired
    private TankeService tankeService;

    @RequestMapping(value = "/acceptTask")
    public ResponseEntity<ResultSetForm> login(@RequestBody Map<String,Object> map) {
        ResultSetForm rsf = new ResultSetForm();
        try {
            if(map.get("account")==null||map.get("password")==null){
                rsf.setResult(false, EnumReturnCode.ReturnCode.Error_Args, " 缺少指定参数");
            }
            // Get param
            String account = String.valueOf(map.get("account"));
            String password = String.valueOf(map.get("password"));
//            String minBenjin = String.valueOf(map.get("minBenjin"));
//            String maxBenjin = String.valueOf(map.get("maxBenjin"));
//            String minYongjin = String.valueOf(map.get("minYongjin"));
//            String maxYongjin = String.valueOf(map.get("maxYongjin"));

            tankeService.login(account, password);// 用户名，和密码

        } catch (Exception e) {
            rsf.setResult(false, EnumReturnCode.ReturnCode.Error, "运行时错误！");
        }
        return new ResponseEntity<ResultSetForm>(rsf, HttpStatus.OK);
    }
}
