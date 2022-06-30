package com.oes.gbloes.controller;

import com.oes.gbloes.controller.util.R;
import com.oes.gbloes.service.ITaskExam;
import com.oes.gbloes.viewmodel.admin.task.TaskEditVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/task")
public class TaskExamController {
    @Autowired
    ITaskExam iTaskExam;

    @PostMapping("/edit")
    public R addTaskExam(@RequestBody TaskEditVM model){
        iTaskExam.addTaskExam(model);
        return new R(true);
    }

    @GetMapping("page/{gradeLevel}/{pageIndex}/{pageSize}")
    public R seleteTaskExamPage(@PathVariable(required = false) Integer gradeLevel,
                                @PathVariable Integer pageIndex,@PathVariable Integer pageSiza){
        return new R(true,iTaskExam.seleteTaskExamPage(gradeLevel,pageIndex,pageSiza));
    }

    @DeleteMapping("delete/{id}")
    public R delteTaskExam(@PathVariable Integer id){
        return new R(iTaskExam.deleteTaskExam(id));
    }
}
