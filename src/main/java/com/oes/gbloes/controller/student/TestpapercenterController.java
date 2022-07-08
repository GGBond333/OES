package com.oes.gbloes.controller.student;

import com.oes.gbloes.controller.util.R;
import com.oes.gbloes.service.IExamPaper;
import com.oes.gbloes.service.ISubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student/papercenter")
public class TestpapercenterController {
    @Autowired
    ISubject iSubject;
    @Autowired
    IExamPaper iExamPaper;

    //获得用户科目
    @GetMapping("subject")
    public R getSubjects(){
        return R.ok(iSubject.getSubjects());
    }

    //通过科目和试卷类型获得试卷分页信息
    @GetMapping("paper/{subjectId}/{paperType}/{pageIndex}/{pageSize}")
    public R getExamPaper(@PathVariable Integer subjectId,@PathVariable Integer paperType,
                          @PathVariable Integer pageIndex,@PathVariable Integer pageSize){
        return R.ok(iExamPaper.getExamPaperBySubjectAndPaperType(subjectId, paperType, pageIndex, pageSize));
    }
}
