package com.oes.gbloes.service.serviceImpl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oes.gbloes.dao.MessageDao;
import com.oes.gbloes.domain.Message;
import com.oes.gbloes.domain.User;
import com.oes.gbloes.service.IMessage;
import com.oes.gbloes.service.IMessageUser;
import com.oes.gbloes.utils.UserUtil;
import com.oes.gbloes.viewmodel.admin.message.MessageSendVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageImpl extends ServiceImpl<MessageDao, Message> implements IMessage {
    @Autowired
    MessageDao messageDao;
    @Autowired
    IMessageUser iMessageUser;



    @Override
    public void addMessage(MessageSendVM model) {
        Message message = new Message();
        message.setTitle(model.getTitle());
        message.setContent(model.getContent());
        message.setCreateTime(DateUtil.date(System.currentTimeMillis()));
        message.setReceiveUserCount(model.getReceiveUserIds().size());
        message.setReadCount(0);
        User user = UserUtil.getUser();
        message.setSendUserId(user.getId());
        message.setSendUserName(user.getUserName());
        message.setSendRealName(user.getUserName());
        messageDao.insert(message);
        iMessageUser.addMessageUsers(message.getId(),model.getReceiveUserIds());
    }

    @Override
    public IPage<Message> selectMessagePage(String SendUserName, Integer pageIndex, Integer pageSize) {
        QueryWrapper<Message> messageQueryWrapper = new QueryWrapper<>();
        if(SendUserName != null){
            messageQueryWrapper.like("send_user_name",SendUserName);
        }
        Page<Message> messagePage = new Page<>(pageIndex,pageSize);
        IPage<Message> iPage = messageDao.selectPage(messagePage,messageQueryWrapper);
        List<Message> messageList = iPage.getRecords();
        for(Message message : messageList){
            message.setUserNameList(iMessageUser.getUserNameByMessageId(message.getId()));
        }
        iPage.setRecords(messageList);
        return iPage;
    }


}
