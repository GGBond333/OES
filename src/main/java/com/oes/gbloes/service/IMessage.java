package com.oes.gbloes.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oes.gbloes.domain.Message;
import com.oes.gbloes.viewmodel.admin.message.MessageSendVM;

public interface IMessage extends IService<Message> {
    public void addMessage(MessageSendVM model);

    public IPage<Message> selectMessagePage(String SendUserName, Integer pageIndex, Integer pageSize);


}
