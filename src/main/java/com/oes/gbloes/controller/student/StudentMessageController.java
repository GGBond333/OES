package com.oes.gbloes.controller.student;

import com.oes.gbloes.controller.util.R;
import com.oes.gbloes.service.IMessageUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student/message")
public class StudentMessageController {
    @Autowired
    IMessageUser iMessageUser;

    //消息分页
    @GetMapping("page/{pageIndex}/{pageSize}")
    public R getMessageInStudent(@PathVariable Integer pageIndex,@PathVariable Integer pageSize){
        return R.ok(iMessageUser.getMessageUserById(pageIndex,pageSize));
    }

    //更新消息已读
    @PutMapping("{id}")
    public R updateReaded(@PathVariable Integer id){
        return R.ok(iMessageUser.updateReaded(id));
    }

    //获得消息未读数量
    @GetMapping("unread")
    public R getUnReadCount(){
        return R.ok(iMessageUser.getUnReaded());
    }
}
