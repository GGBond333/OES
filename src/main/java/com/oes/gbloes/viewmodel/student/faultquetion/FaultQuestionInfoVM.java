package com.oes.gbloes.viewmodel.student.faultquetion;

import com.oes.gbloes.viewmodel.admin.question.QuestionEditRequestVM;
import com.oes.gbloes.viewmodel.student.answer.ExamPaperSubmitItemVM;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FaultQuestionInfoVM {
    private QuestionEditRequestVM questionVM;
    private ExamPaperSubmitItemVM questionAnswerVM;
}
