package com.oes.gbloes.controller;

import com.oes.gbloes.controller.util.R;
import com.oes.gbloes.service.IMessage;
import com.oes.gbloes.viewmodel.admin.message.MessageSendVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/message")
public class MessageController {
    @Autowired
    IMessage iMessage;

    @PostMapping("add")
    public R addMessage(@RequestBody MessageSendVM messageSendVM){
        iMessage.addMessage(messageSendVM);
        return R.ok(true);
    }

    @GetMapping(value = {"page/{sendUserName}/{pageIndex}/{pageSize}","page/{pageIndex}/{pageSize}"})
    public R getMessages(@PathVariable(required = false) String sendUserName,
                         @PathVariable Integer pageIndex,@PathVariable Integer pageSize){
        return R.ok(iMessage.selectMessagePage(sendUserName, pageIndex, pageSize));
    }
}
