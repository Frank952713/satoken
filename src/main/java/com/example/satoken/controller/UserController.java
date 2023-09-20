package com.example.satoken.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {


    @RequestMapping("/doLogin")
    public String doLogin(String username, String password) {
        if ("zhangsan".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            return "登录成功";
        }
        return "登录失败";
    }

    @RequestMapping("/isLogin")
    public String isLogin() {
        return "当前会话是否登录："+StpUtil.isLogin();
    }

}
