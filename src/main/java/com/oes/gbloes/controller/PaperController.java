package com.oes.gbloes.controller;

import com.oes.gbloes.controller.util.R;
import com.oes.gbloes.service.IExamPaper;
import com.oes.gbloes.viewmodel.admin.paper.ExamPaperEditRequestVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/exampaper")
public class PaperController {
    @Autowired
    IExamPaper iExamPaper;

    @PutMapping()
    public R updatePaper(@RequestBody ExamPaperEditRequestVM model){
        iExamPaper.updateExamPaper(model);
        return R.ok(true);
    }
    @PostMapping("/add")
    public R addPaper(@RequestBody ExamPaperEditRequestVM model){
        iExamPaper.addExamPaper(model);
        return R.ok(true);
    }
    @GetMapping(value = {"/list/{levelStr}/{subjectIdStr}/{pageIndex}/{pageSize}","/list/{pageIndex}/{pageSize}"})
    public R getPaperPage(@PathVariable(required = false) String levelStr,@PathVariable(required = false) String subjectIdStr,
                          @PathVariable Integer pageIndex,@PathVariable Integer pageSize){
        Integer subjectId = null;
        Integer level = null;
        if(!(subjectIdStr==null)&&(!subjectIdStr.equals("null"))){
            subjectId = Integer.valueOf(subjectIdStr);
        }
        if(!(levelStr==null)&&(!levelStr.equals("null"))){
            level = Integer.valueOf(levelStr);
        }
        return R.ok(iExamPaper.getExamPaperPage(level,subjectId,pageIndex,pageSize));
    }

    @DeleteMapping("delete/{id}")
    public R deletePaper(@PathVariable Integer id){
        return R.ok(iExamPaper.deleteExamPaper(id));
    }

    @GetMapping("{id}")
    public R getPaperById(@PathVariable Integer id){
        return R.ok(iExamPaper.getExamPaperRequestVM(id));
    }

    @GetMapping("paper/{id}")
    public R getPaper(@PathVariable Integer id){
        return R.ok(iExamPaper.getById(id));
    }
}
