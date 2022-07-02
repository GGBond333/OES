package com.oes.gbloes;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.oes.gbloes.domain.Message;
import com.oes.gbloes.domain.User;
import com.oes.gbloes.service.IMessage;
import com.oes.gbloes.service.IUser;
import com.oes.gbloes.viewmodel.admin.message.MessageSendVM;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MessageTest {
    @Autowired
    IMessage iMessage;
    @Autowired
    IUser iUser;
    @Test
    public void addMessage(){
        MessageSendVM model = new MessageSendVM();
        model.setTitle("你好!!");
        model.setContent("你们好!!");
        List<Integer> receiveUserCount = new ArrayList<>();
        receiveUserCount.add(1);
        receiveUserCount.add(2);
        receiveUserCount.add(3);
        User user = iUser.getUser("root","123456");
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        session.setAttribute("userInfo", user);
        model.setReceiveUserIds(receiveUserCount);
        iMessage.addMessage(model);

    }

    @Test
    public void getMessage(){
        IPage<Message> messageIPage = iMessage.selectMessagePage(null,1,10);
        List<Message> messageList = messageIPage.getRecords();
        for(Message message : messageList){
            System.out.println(message);
        }
    }
}
