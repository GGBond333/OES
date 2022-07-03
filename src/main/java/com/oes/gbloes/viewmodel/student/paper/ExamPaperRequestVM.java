package com.oes.gbloes.viewmodel.student.paper;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class ExamPaperRequestVM {
    private Integer id;
    private String name;
    private Integer subjectId;
    private Integer paperType;
    private Integer gradeLevel;
    private Integer score;
    private Integer suggestTime;
    private Date LimiteStartTime;
    private Date LimiteEndTime;
    private List<ExamPaperTitleItemRequestVM> titleItems;
}
