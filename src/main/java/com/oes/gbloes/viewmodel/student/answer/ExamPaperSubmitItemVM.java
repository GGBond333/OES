package com.oes.gbloes.viewmodel.student.answer;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ExamPaperSubmitItemVM {
    private Integer questionId;
    private String content;
    private List<String> contenArray;
    private Boolean completed;
    private Integer itemOrder;
}
