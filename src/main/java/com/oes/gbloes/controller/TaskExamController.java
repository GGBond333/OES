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

    @PutMapping
    public R editTaskExam(@RequestBody TaskEditVM model){
        iTaskExam.updateTaskExam(model);
        return R.ok(true);
    }


    @PostMapping("/edit")
    public R addTaskExam(@RequestBody TaskEditVM model){
        iTaskExam.addTaskExam(model);
        return R.ok(true);
    }

    @GetMapping(value = {"page/{gradeLevel}/{pageIndex}/{pageSize}","page/{pageIndex}/{pageSize}"})
    public R seleteTaskExamPage(@PathVariable(required = false) Integer gradeLevel,
                                @PathVariable Integer pageIndex,@PathVariable Integer pageSize){
        return R.ok(iTaskExam.seleteTaskExamPage(gradeLevel,pageIndex,pageSize));
    }

    @DeleteMapping("delete/{id}")
    public R delteTaskExam(@PathVariable Integer id){
        return R.ok(iTaskExam.deleteTaskExam(id));
    }

    @GetMapping("{id}")
    public R getTaskExam(@PathVariable Integer id){
        return R.ok(iTaskExam.getTask(id));
    }
}
