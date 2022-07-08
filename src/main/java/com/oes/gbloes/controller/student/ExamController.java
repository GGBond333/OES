package com.oes.gbloes.controller.student;

import com.oes.gbloes.controller.util.R;
import com.oes.gbloes.service.IExamPaper;
import com.oes.gbloes.service.IExamPaperAnswer;
import com.oes.gbloes.viewmodel.student.answer.ExamPaperSubmitVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student/exam")
public class ExamController {
    @Autowired
    IExamPaper iExamPaper;
    @Autowired
    IExamPaperAnswer iExamPaperAnswer;


    //通过试卷id获得考卷
    @GetMapping("/{id}")
    public R getExamPaperById(@PathVariable Integer id){
        return R.ok(iExamPaper.getExamPaperRequestVM(id));
    }

    //提交考卷
    @PostMapping("/submit")
    public R submitPaperAnswer(@RequestBody ExamPaperSubmitVM examPaperSubmitVM){

        return R.ok(iExamPaperAnswer.sumbitExamPaperAnswer(examPaperSubmitVM));
    }
}
