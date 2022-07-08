package com.oes.gbloes.controller.student;

import com.oes.gbloes.controller.util.R;
import com.oes.gbloes.service.IExamPaperAnswer;
import com.oes.gbloes.viewmodel.student.answer.ExamPaperSubmitVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student/answer")
public class ExamAnswerController {
    @Autowired
    IExamPaperAnswer iExamPaperAnswer;

    //答卷分页
    @GetMapping("pageList/{pageIndex}/{pageSize}")
    public R getAnswerPage(@PathVariable Integer pageIndex, @PathVariable Integer pageSize){
        return R.ok(iExamPaperAnswer.getExamPaperAnswerPage(pageIndex,pageSize));
    }

    //查看某一个答卷的详细信息
    @GetMapping("read/{id}")
    public R getAnswerById(@PathVariable Integer id){
        return R.ok(iExamPaperAnswer.getAnswerPaper(id));
    }

    @PutMapping()
    public R updateAnswerPaper(@RequestBody ExamPaperSubmitVM examPaperSubmitVM){
        return R.ok(iExamPaperAnswer.updateExamAnswerPaper(examPaperSubmitVM));
    }
}
