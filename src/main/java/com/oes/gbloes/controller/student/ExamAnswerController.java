package com.oes.gbloes.controller.student;

import com.oes.gbloes.controller.util.R;
import com.oes.gbloes.service.IExamPaperAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student/answer")
public class ExamAnswerController {
    @Autowired
    IExamPaperAnswer iExamPaperAnswer;

    @GetMapping("pageList/{pageIndex}/{pageSize}")
    public R getAnswerPage(@PathVariable Integer pageIndex, @PathVariable Integer pageSize){
        return R.ok(iExamPaperAnswer.getExamPaperAnswerPage(pageIndex,pageSize));
    }

    @GetMapping("read/{id}")
    public R getAnswerById(@PathVariable Integer id){
        return R.ok(iExamPaperAnswer.getAnswerPaper(id));
    }
}
