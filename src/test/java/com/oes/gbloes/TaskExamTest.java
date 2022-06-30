package com.oes.gbloes;

import com.oes.gbloes.service.ITaskExam;
import com.oes.gbloes.viewmodel.admin.task.TaskEditVM;
import com.oes.gbloes.viewmodel.admin.task.TaskPaperVM;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TaskExamTest {
    @Autowired
    ITaskExam taskExam;
    @Test
    public void addTaskExam(){
        List<TaskPaperVM> taskPaperVMList = new ArrayList<>();
        TaskPaperVM taskPaperVM = new TaskPaperVM();
        taskPaperVM.setExamPaperId(1);
        taskPaperVM.setExamPaperName("你好");
        taskPaperVM.setItemOrder(1);
        taskPaperVMList.add(taskPaperVM);
        TaskPaperVM taskPaperVM1 = new TaskPaperVM();
        taskPaperVM1.setExamPaperId(2);
        taskPaperVM1.setExamPaperName("你好!!!");
        taskPaperVM1.setItemOrder(2);
        taskPaperVMList.add(taskPaperVM1);
        TaskEditVM taskEditVM = new TaskEditVM();
        taskEditVM.setItems(taskPaperVMList);
        taskEditVM.setGradeLevel(9);
        taskEditVM.setTitle("每日任务");
        taskExam.addTaskExam(taskEditVM);
    }

}
