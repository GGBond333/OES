package com.oes.gbloes.viewmodel.student.index;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class TaskPaperVM {
    private String taskTitle;
    private List<TaskPaperInfoVM> taskPapers;
}
