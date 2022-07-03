package com.oes.gbloes.viewmodel.student.paper;

import com.oes.gbloes.viewmodel.admin.question.QuestionEditRequestVM;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ExamPaperTitleItemRequestVM {
    private String name;
    private List<QuestionEditRequestVM> questionItems;
}
