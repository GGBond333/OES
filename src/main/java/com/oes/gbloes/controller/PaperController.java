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

    @PostMapping("/add")
    public R addPaper(@RequestBody ExamPaperEditRequestVM model){
        iExamPaper.addExamPaper(model);
        return new R(true);
    }
    @GetMapping(value = {"/list/{level}/{subjectId}/{pageIndex}/{pageSize}"})
    public R getPaperPage(@PathVariable(required = false) Integer level,@PathVariable(required = false) Integer subjectId,
                          @PathVariable Integer pageIndex,@PathVariable Integer pageSize){
        return new R(true,iExamPaper.getExamPaperPage(level,subjectId,pageIndex,pageSize));
    }

    @DeleteMapping("delete/{id}")
    public R deletePaper(@PathVariable Integer id){
        return new R(iExamPaper.deleteExamPaper(id));
    }

}
