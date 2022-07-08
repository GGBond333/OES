package com.oes.gbloes.service.serviceImpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oes.gbloes.dao.TaskExamDao;
import com.oes.gbloes.dao.TextContentDao;
import com.oes.gbloes.domain.TaskExam;
import com.oes.gbloes.domain.TextContent;
import com.oes.gbloes.domain.User;
import com.oes.gbloes.domain.task.TaskPaper;
import com.oes.gbloes.service.ITaskExam;
import com.oes.gbloes.utils.JsonUtil;
import com.oes.gbloes.utils.UserUtil;
import com.oes.gbloes.viewmodel.admin.task.TaskEditVM;
import com.oes.gbloes.viewmodel.student.index.TaskPaperInfoVM;
import com.oes.gbloes.viewmodel.student.index.TaskPaperVM;
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

        List<TaskPaper> taskPaperList = model.getPaperItems().stream().map(i->{
            TaskPaper taskPaper = new TaskPaper();
            taskPaper.setId(i.getId());
            taskPaper.setName(i.getName());
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

    @Override
    public List<TaskPaperVM> getTaskPaperInfo() {
        User user = UserUtil.getUser();
        QueryWrapper<TaskExam> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("grade_level",user.getUserLevel()).orderByDesc("create_time").last("limit 8");

        List<TaskExam> taskExams = taskExamDao.selectList(queryWrapper);
        List<TaskPaperVM> taskPaperVMList = taskExams.stream().map(i->{
            TaskPaperVM taskPaperVM = new TaskPaperVM();
            TextContent textContent = textContentDao.selectById(i.getFrameTextContentId());
            List<TaskPaperInfoVM> taskPaperInfoVMList = JSONUtil.toList(textContent.getContent(), TaskPaperInfoVM.class);
            taskPaperVM.setTaskPapers(taskPaperInfoVMList);
            taskPaperVM.setTaskTitle(i.getTitle());
            return taskPaperVM;
        }).collect(Collectors.toList());

        return taskPaperVMList;
    }

    @Override
    public void updateTaskExam(TaskEditVM model) {
        TaskExam taskExam = new TaskExam();
        taskExam.setId(model.getId());
        taskExam.setGradeLevel(model.getGradeLevel());
        taskExam.setTitle(model.getTitle());
        taskExamDao.updateById(taskExam);

        TaskExam taskExam1 = taskExamDao.selectById(model.getId());

        List<TaskPaper> taskPaperList = model.getPaperItems().stream().map(i->{
            TaskPaper taskPaper = new TaskPaper();
            taskPaper.setId(i.getId());
            taskPaper.setName(i.getName());
            taskPaper.setItemOrder(i.getItemOrder());
            return taskPaper;
        }).collect(Collectors.toList());
        TextContent textContent = new TextContent();
        textContent.setId(taskExam1.getFrameTextContentId());
        textContent.setContent(JsonUtil.toJsonStr(taskPaperList));
        textContentDao.updateById(textContent);
    }

    @Override
    public TaskEditVM getTask(Integer id) {
        TaskEditVM taskEditVM = new TaskEditVM();

        TaskExam taskExam = taskExamDao.selectById(id);
        TextContent textContent = textContentDao.selectById(taskExam.getFrameTextContentId());
        List<com.oes.gbloes.viewmodel.admin.task.TaskPaperVM> taskPaperVMList = JSONUtil.toList(textContent.getContent(), com.oes.gbloes.viewmodel.admin.task.TaskPaperVM.class);
        taskEditVM.setPaperItems(taskPaperVMList);
        taskEditVM.setId(taskExam.getId());
        taskEditVM.setTitle(taskExam.getTitle());
        taskEditVM.setGradeLevel(taskExam.getGradeLevel());
        return taskEditVM;
    }
}
