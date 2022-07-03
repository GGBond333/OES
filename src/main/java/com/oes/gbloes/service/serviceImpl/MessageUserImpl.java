package com.oes.gbloes.service.serviceImpl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oes.gbloes.dao.MessageDao;
import com.oes.gbloes.dao.MessageUserDao;
import com.oes.gbloes.domain.Message;
import com.oes.gbloes.domain.MessageUser;
import com.oes.gbloes.domain.User;
import com.oes.gbloes.service.IMessageUser;
import com.oes.gbloes.service.IUser;
import com.oes.gbloes.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageUserImpl extends ServiceImpl<MessageUserDao, MessageUser> implements IMessageUser {
    @Autowired
    MessageUserDao messageUserDao;
    @Autowired
    MessageDao messageDao;
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

    @Override
    public IPage<MessageUser> getMessageUserById(Integer pageIndex, Integer pageSize) {
        User user = UserUtil.getUser();
        QueryWrapper<MessageUser> messageUserQueryWrapper = new QueryWrapper<>();
        messageUserQueryWrapper.eq("receive_user_id",user.getId());
        Page<MessageUser> page = new Page<>(pageIndex,pageSize);
        IPage<MessageUser> iPage = messageUserDao.selectPage(page,messageUserQueryWrapper);
        List<MessageUser> messageUserList = iPage.getRecords();
        for(MessageUser messageUser : messageUserList){
            Message message = messageDao.selectById(messageUser.getMessageId());
            messageUser.setTitle(message.getTitle());
            messageUser.setContent(message.getContent());
            messageUser.setSendUserName(messageUser.getSendUserName());
        }
        iPage.setRecords(messageUserList);
        return iPage;
    }

    @Override
    public Boolean updateReaded(Integer id) {
        MessageUser messageUser = new MessageUser();
        messageUser.setId(id);
        messageUser.setReaded(true);
        return messageUserDao.updateById(messageUser)>0;
    }

    @Override
    public Integer getUnReaded() {
        User user = UserUtil.getUser();
        QueryWrapper<MessageUser> messageUserQueryWrapper = new QueryWrapper<>();
        messageUserQueryWrapper.eq("receive_user_id",user.getId()).eq("readed",0);
        List<MessageUser> messageUserList = messageUserDao.selectList(messageUserQueryWrapper);
        return messageUserList.size();
    }
}
