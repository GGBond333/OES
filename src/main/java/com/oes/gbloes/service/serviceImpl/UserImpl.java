package com.oes.gbloes.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oes.gbloes.dao.UserDao;
import com.oes.gbloes.domain.User;
import com.oes.gbloes.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UserImpl extends ServiceImpl<UserDao,User>  implements IUser {
    @Autowired
    private UserDao userDao;

    @Override
    public boolean isUserInfo(String userName, String password) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        userQueryWrapper.eq("user_name",userName);
        userQueryWrapper.eq("password",password);
        User user = userDao.selectOne(userQueryWrapper);
        if(user==null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public User getUser(String userName, String password) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        userQueryWrapper.eq("user_name",userName);
        userQueryWrapper.eq("password",password);
        return userDao.selectOne(userQueryWrapper);

    }

    @Override
    public IPage<User> getUserPage(String userName, Integer role, Integer pageIndex, Integer pageSize) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        Page<User> page = new Page<>(pageIndex,pageSize);
        wrapper.eq("role",role);
        //根据条件查询数据
        if(userName!=null){
            wrapper.like("user_name",userName);
        }
        IPage<User> iPage = this.userDao.selectPage(page,wrapper);
        return iPage;
    }

    @Override
    public Boolean modifyUser(User user) {
        Calendar now = Calendar.getInstance();

        User user1 = new User();
        user1.setId(user.getId());
        user1.setUserName(user.getUserName());
        user1.setPassword(user.getPassword());
        user1.setRealName(user.getRealName());
        user1.setRole(user.getRole());
        user1.setStatus(user.getStatus());
        user1.setAge(user.getAge());
        user1.setSex(user.getSex());
        user1.setBirthDay(user.getBirthDay());
        user1.setPhone(user.getPhone());
        user1.setUserLevel(user.getUserLevel());
        user1.setModifyTime(now.getTime());

        return this.userDao.updateById(user1)>0;
    }

    @Override
    public Boolean deleteUser(Integer id) {

        return userDao.deleteById(id)>0;
    }

    @Override
    public Integer changeUserStatus(Integer id) {
        //获取当前状态
        User user = this.userDao.selectById(id);
        Integer status = user.getStatus();
        //设置修改状态
        User user1 = new User();
        user1.setId(id);
        if(status==1){
            status = 2;
        }else{
            status = 1;
        }
        user1.setStatus(status);
        this.userDao.updateById(user1);

        return status;
    }

    @Override
    public Boolean addUser(User user) {
        Calendar now = Calendar.getInstance();
        user.setCreateTime(now.getTime());
        user.setDeleted(false);
        return userDao.insert(user)>0;
    }
}
