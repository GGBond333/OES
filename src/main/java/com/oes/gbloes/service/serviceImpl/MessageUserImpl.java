package com.oes.gbloes.service.serviceImpl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oes.gbloes.dao.MessageUserDao;
import com.oes.gbloes.domain.MessageUser;
import com.oes.gbloes.domain.User;
import com.oes.gbloes.service.IMessageUser;
import com.oes.gbloes.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageUserImpl extends ServiceImpl<MessageUserDao, MessageUser> implements IMessageUser {
    @Autowired
    MessageUserDao messageUserDao;
    @Autowired
    IUser iUser;
    @Override
    public void addMessageUsers(Integer messageId, List<Integer> receiveUserIds) {
        for(Integer userId : receiveUserIds){
            MessageUser messageUser = new MessageUser();
            messageUser.setMessageId(messageId);
            User user = iUser.getUserById(userId);
            messageUser.setReceiveUserId(user.getId());
            messageUser.setReceiveUserName(user.getUserName());
            messageUser.setReceiveRealName(user.getRealName());
            messageUser.setReaded(false);
            messageUser.setCreateTime(DateUtil.date(System.currentTimeMillis()));
            messageUserDao.insert(messageUser);
        }
    }

    @Override
    public List<String> getUserNameByMessageId(Integer messageId) {
        QueryWrapper<MessageUser> messageUserQueryWrapper = new QueryWrapper<>();
        messageUserQueryWrapper.eq("message_id",messageId);
        List<MessageUser> messageUserList = messageUserDao.selectList(messageUserQueryWrapper);
        List<String> userNameList = new ArrayList<>();
        for(MessageUser messageUser : messageUserList){
            userNameList.add(messageUser.getReceiveUserName());
        }
        return userNameList;
    }
}
