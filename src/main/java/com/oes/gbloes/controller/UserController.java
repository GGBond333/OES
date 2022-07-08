package com.oes.gbloes.controller;


import com.oes.gbloes.controller.util.R;
import com.oes.gbloes.domain.User;
import com.oes.gbloes.service.IUser;
import com.oes.gbloes.viewmodel.LoginVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
    @Autowired
    IUser iUser;

    //登录
    @PostMapping("/login")
    public R login(@RequestBody LoginVM user){
//        String userName = data.get("userName");
//        String password = data.get("password");
        System.out.println(user.getUserName());
        if(iUser.isUserInfo(user.getUserName(),user.getPassword())){
            //将用户信息存入到session中
            User user1 = iUser.getUser(user.getUserName(),user.getPassword());
            HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
            session.setAttribute("userInfo", user1);
            return R.ok(true);
        }else {
            return R.ok(false);
        }
    }

    //注销
    @GetMapping("/loginout")
    public R loginOut(){
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        session.removeAttribute("userInfo");
        return R.ok(true);
    }


}
