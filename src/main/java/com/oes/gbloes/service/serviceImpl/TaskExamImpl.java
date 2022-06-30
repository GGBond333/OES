package com.oes.gbloes.service.serviceImpl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oes.gbloes.dao.TaskExamDao;
import com.oes.gbloes.dao.TextContentDao;
import com.oes.gbloes.domain.TaskExam;
import com.oes.gbloes.domain.TextContent;
import com.oes.gbloes.domain.task.TaskPaper;
import com.oes.gbloes.service.ITaskExam;
import com.oes.gbloes.utils.JsonUtil;
import com.oes.gbloes.viewmodel.admin.task.TaskEditVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskExamImpl extends ServiceImpl<TaskExamDao, TaskExam> implements ITaskExam {
    @Autowired
    TaskExamDao taskExamDao;
    @Autowired
    TextContentDao textContentDao;

    @Override
    public void addTaskExam(TaskEditVM model) {
        TaskExam taskExam = new TaskExam();

        List<TaskPaper> taskPaperList = model.getItems().stream().map(i->{
            TaskPaper taskPaper = new TaskPaper();
            taskPaper.setExamPaperId(i.getExamPaperId());
            taskPaper.setExamPaperName(i.getExamPaperName());
            taskPaper.setItemOrder(i.getItemOrder());
            return taskPaper;
        }).collect(Collectors.toList());
        TextContent textContent = new TextContent();
        textContent.setCreateTime(DateUtil.date(Calendar.getInstance()));
        textContent.setContent(JsonUtil.toJsonStr(taskPaperList));
        textContentDao.insert(textContent);

        taskExam.setCreateTime(DateUtil.date(Calendar.getInstance()));
        taskExam.setGradeLevel(model.getGradeLevel());
        taskExam.setTitle(model.getTitle());
        taskExam.setFrameTextContentId(textContent.getId());
        taskExam.setDeleted(false);
        taskExamDao.insert(taskExam);

    }

    @Override
    public IPage<TaskExam> seleteTaskExamPage(Integer gradeLevel, Integer pageIndex, Integer pageSize) {
        QueryWrapper<TaskExam> taskExamQueryWrapper = new QueryWrapper<>();
        taskExamQueryWrapper.eq("deleted",0);
        if(gradeLevel!=null){
            taskExamQueryWrapper.eq("grade_level",gradeLevel);
        }
        Page<TaskExam> page = new Page<>(pageIndex,pageSize);
        IPage<TaskExam> iPage = taskExamDao.selectPage(page,taskExamQueryWrapper);

        return iPage;
    }

    @Override
    public Boolean deleteTaskExam(Integer id) {
        TaskExam taskExam = new TaskExam();
        taskExam.setId(id);
        taskExam.setDeleted(true);
        return taskExamDao.updateById(taskExam)>0;
    }
}
