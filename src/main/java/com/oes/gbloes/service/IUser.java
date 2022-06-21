package com.oes.gbloes.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oes.gbloes.domain.User;

import java.util.List;

public interface IUser extends IService<User> {
    //判断用户登录信息是否正确
    boolean isUserInfo(String userName, String password);

    User getUser(String userName, String password);

    //用户查询分页
    IPage<User> getUserPage(String userName, Integer role, Integer pageIndex, Integer pageSize);

    //用户修改分页
    Boolean modifyUser(User user);

    //用户删除
    Boolean deleteUser(Integer id);

    //用户状态更改
    Integer changeUserStatus(Integer id);

    //用户添加
    Boolean addUser(User user);
}
