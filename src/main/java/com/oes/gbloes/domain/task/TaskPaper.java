package com.oes.gbloes.domain.task;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TaskPaper {
    private Integer examPaperId;
    private String examPaperName;
    private Integer itemOrder;
}
