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
        return new R(true);
    }

    @GetMapping(value = {"/list/{questionType}/{subjectId}/{level}/{pageIndex}/{pageSize}"})
    public R getQuestionPage(@PathVariable(required = false) Integer questionType,@PathVariable(required = false) Integer subjectId,
                             @PathVariable(required = false) Integer level,@PathVariable Integer pageIndex,@PathVariable Integer pageSize){

        return new R(true,iQuestion.getQuestionPge(questionType,subjectId,level,pageIndex,pageSize));
    }

    @DeleteMapping("delete/{id}")
    public R deleteQuetion(@PathVariable Integer id){
        iQuestion.deleteQuestion(id);
        return new R(true);

    }
}
