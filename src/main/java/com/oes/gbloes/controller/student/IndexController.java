package com.oes.gbloes.controller.student;

import com.oes.gbloes.controller.util.R;
import com.oes.gbloes.service.IExamPaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student/index")
public class IndexController {
    @Autowired
    IExamPaper iExamPaper;

    //首页信息
    @GetMapping
    public R getIndex(){
        return R.ok(iExamPaper.getIndexInfo());
    }
}
