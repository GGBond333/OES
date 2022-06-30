package com.oes.gbloes.utils;

import com.oes.gbloes.domain.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class UserUtil {
    public static User getUser(){
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        Object userObj =  session.getAttribute("userInfo");
        User user = (User) userObj;
        return user;
    }
}
