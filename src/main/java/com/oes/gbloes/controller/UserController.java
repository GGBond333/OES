package com.oes.gbloes.controller;


import com.oes.gbloes.controller.util.R;
import com.oes.gbloes.domain.User;
import com.oes.gbloes.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    IUser iUser;

    //登录
    @PostMapping("/login")
    public R login(@RequestBody Map<String, String> data){
        String userName = data.get("userName");
        String password = data.get("password");

        if(iUser.isUserInfo(userName,password)){
            //将用户信息存入到session中
            User user = iUser.getUser(userName,password);
            HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
            session.setAttribute("userInfo", user);
            return new R(true,"登录成功");
        }else {
            return new R(false,"用户名或密码错误");
        }
    }

    //注销
    @GetMapping("/loginout")
    public R loginOut(){
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        session.removeAttribute("userInfo");
        return new R(true);
    }


}
