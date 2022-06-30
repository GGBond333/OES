package com.oes.gbloes.viewmodel.admin.task;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TaskPaperVM {
    private Integer examPaperId;
    private String examPaperName;
    private Integer itemOrder;
}
