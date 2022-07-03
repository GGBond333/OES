package com.oes.gbloes.viewmodel.student.answer;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ExamPaperSubmitVM {
    private Integer id;
    private Integer doTime;
    private List<ExamPaperSubmitItemVM> answerItems;

}
