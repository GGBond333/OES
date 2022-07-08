package com.oes.gbloes.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oes.gbloes.domain.MessageUser;

import java.util.List;

public interface IMessageUser extends IService<MessageUser> {
    public void addMessageUsers(Integer messageId, List<Integer> receiveUserIds);

    public String getUserNameByMessageId(Integer messageId);

    //学生消息列表
    public IPage<MessageUser> getMessageUserById(Integer pageIndex,Integer pageSize);

    //设置已读
    public Boolean updateReaded(Integer id);

    //获取用户未读消息的数量
    public Integer getUnReaded();

}
