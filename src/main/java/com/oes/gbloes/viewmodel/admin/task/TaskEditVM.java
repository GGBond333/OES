package com.oes.gbloes.viewmodel.admin.task;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class TaskEditVM {
    private Integer id;
    private String title;
    private Integer gradeLevel;
    private List<TaskPaperVM> paperItems;
}
