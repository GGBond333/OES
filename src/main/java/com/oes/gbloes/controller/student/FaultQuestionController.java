package com.oes.gbloes.controller.student;

import com.oes.gbloes.controller.util.R;
import com.oes.gbloes.service.IExamPaperQuestionCustomerAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student/faultQuestion")
public class FaultQuestionController {
    @Autowired
    IExamPaperQuestionCustomerAnswer iExamPaperQuestionCustomerAnswer;

    //错题本分页
    @GetMapping("page/{pageIndex}/{pageSize}")
    public R getFaultQuestions(@PathVariable Integer pageIndex,@PathVariable Integer pageSize){
        return R.ok(iExamPaperQuestionCustomerAnswer.getFaultQuestion(pageIndex,pageSize));
    }

    //详细错题信息
    @GetMapping("select/{id}")
    public R getFaultQuestionInfoById(@PathVariable Integer id){
        return R.ok(iExamPaperQuestionCustomerAnswer.getFaultQuestionById(id));
    }
}
