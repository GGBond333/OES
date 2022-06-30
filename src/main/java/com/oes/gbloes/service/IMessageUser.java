package com.oes.gbloes.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oes.gbloes.domain.MessageUser;

import java.util.List;

public interface IMessageUser extends IService<MessageUser> {
    public void addMessageUsers(Integer messageId, List<Integer> receiveUserIds);

    public List<String> getUserNameByMessageId(Integer messageId);
}
