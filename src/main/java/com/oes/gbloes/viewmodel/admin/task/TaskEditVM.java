package com.oes.gbloes.viewmodel.admin.task;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class TaskEditVM {
    private String Title;
    private Integer gradeLevel;
    private List<TaskPaperVM> items;
}
