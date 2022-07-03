package com.oes.gbloes.controller;

import com.oes.gbloes.controller.util.R;
import com.oes.gbloes.service.IQuestion;
import com.oes.gbloes.viewmodel.admin.question.QuestionEditRequestVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/question")
public class QuestionController {
    @Autowired
    IQuestion iQuestion;

    @PostMapping("/add")
    public R addQuestion(@RequestBody QuestionEditRequestVM model){
        iQuestion.insertQuestion(model);
        return R.ok(true);
    }

    @GetMapping(value = {"/list/{pageIndex}/{pageSize}/{questionType}/{subjectId}/{level}","/list/{pageIndex}/{pageSize}"})
    public R getQuestionPage(@PathVariable Integer pageIndex,@PathVariable Integer pageSize,@PathVariable(required = false) Integer questionType,@PathVariable(required = false) Integer subjectId,
                             @PathVariable(required = false) Integer level){

        return R.ok(iQuestion.getQuestionPge(questionType,subjectId,level,pageIndex,pageSize));
    }

    @DeleteMapping("delete/{id}")
    public R deleteQuetion(@PathVariable Integer id){
        iQuestion.deleteQuestion(id);
        return R.ok(true);

    }
}
