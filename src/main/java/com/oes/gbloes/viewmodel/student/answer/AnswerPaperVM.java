package com.oes.gbloes.viewmodel.student.answer;

import com.oes.gbloes.viewmodel.student.paper.ExamPaperRequestVM;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class AnswerPaperVM {
    private ExamPaperRequestVM paper;
    private AnswerVM answer;
}
