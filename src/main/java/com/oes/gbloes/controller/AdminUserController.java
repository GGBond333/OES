package com.oes.gbloes.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.oes.gbloes.controller.util.R;
import com.oes.gbloes.domain.User;
import com.oes.gbloes.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("admin/user")
public class AdminUserController {
    @Autowired
    IUser iUser;

    //查询或默认
    @GetMapping(value = {"/page/list/{userName}/{role}/{pageIndex}/{pageSize}","/page/list/{role}/{pageIndex}/{pageSize}"})
    public R getUserPage(@PathVariable(required = false) String userName,@PathVariable Integer role,@PathVariable Integer pageIndex,@PathVariable Integer pageSize){
        IPage<User> iPage = iUser.getUserPage(userName,role,pageIndex,pageSize);
        //return new R(true,iPage);
        return R.ok(iPage);
    }

    //编辑
    @PutMapping("/edit")
    public R modifyUser(User user){
        System.out.println(user);
        return R.ok(iUser.modifyUser(user));

    }

    @DeleteMapping("/delete/{id}")
    public R deleteUser(@PathVariable Integer id){
        return R.ok(iUser.deleteUser(id));
    }

    @PutMapping("/changeStatus/{id}")
    public R changeUserStaus(@PathVariable Integer id){
        Integer status = iUser.changeUserStatus(id);
        return R.ok(status);
    }

    @PostMapping("/add")
    public R addUser(User user){
        return R.ok(iUser.addUser(user));
    }

    @GetMapping("selete/{id}")
    public R selteUserById(@PathVariable Integer id){
        return R.ok(iUser.getUserById(id));
    }
}
