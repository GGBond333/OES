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

    @GetMapping(value = {"/list/{pageIndex}/{pageSize}/{questionTypeStr}/{subjectIdStr}/{levelStr}","/list/{pageIndex}/{pageSize}"})
    public R getQuestionPage(@PathVariable Integer pageIndex,@PathVariable Integer pageSize,@PathVariable(required = false) String questionTypeStr,@PathVariable(required = false) String subjectIdStr,
                             @PathVariable(required = false) String levelStr){
        Integer questionType = null;
        Integer subjectId = null;
        Integer level = null;
        if(!(questionTypeStr==null)&&(!questionTypeStr.equals("null"))){
            questionType = Integer.valueOf(questionTypeStr);
        }
        if(!(subjectIdStr==null)&&(!subjectIdStr.equals("null"))){
            subjectId = Integer.valueOf(subjectIdStr);
        }
        if(!(levelStr==null)&&(!levelStr.equals("null"))){
            level = Integer.valueOf(levelStr);
        }
        return R.ok(iQuestion.getQuestionPge(questionType,subjectId,level,pageIndex,pageSize));
    }

    @DeleteMapping("delete/{id}")
    public R deleteQuetion(@PathVariable Integer id){
        iQuestion.deleteQuestion(id);
        return R.ok(true);

    }

    @GetMapping("{id}")
    public R getQuetionById(@PathVariable Integer id){
        return R.ok(iQuestion.getQuestionById(id));
    }

    @PutMapping
    public R updateQuestion(@RequestBody QuestionEditRequestVM model){
        iQuestion.updateQuetion(model);
        return R.ok(true);
    }
}
