package com.oes.gbloes.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oes.gbloes.domain.TaskExam;
import com.oes.gbloes.viewmodel.admin.task.TaskEditVM;

public interface ITaskExam extends IService<TaskExam> {
    public void addTaskExam(TaskEditVM model);

    public IPage<TaskExam> seleteTaskExamPage(Integer gradeLevel,Integer pageIndex,Integer pageSize);

    public Boolean deleteTaskExam(Integer id);
}
