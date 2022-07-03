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

    @GetMapping("page/{pageIndex}/{pageSize}")
    public R getMessageInStudent(@PathVariable Integer pageIndex,@PathVariable Integer pageSize){
        return R.ok(iMessageUser.getMessageUserById(pageIndex,pageSize));
    }

    @PutMapping()
    public R updateReaded(Integer id){
        return R.ok(iMessageUser.updateReaded(id));
    }

    @GetMapping("unread")
    public R getUnReadCount(){
        return R.ok(iMessageUser.getUnReaded());
    }
}
