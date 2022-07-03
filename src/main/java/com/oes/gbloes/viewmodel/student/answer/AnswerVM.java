package com.oes.gbloes.viewmodel.student.answer;

import com.oes.gbloes.domain.ExamPaperQuestionCustomerAnswer;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class AnswerVM {
    private Integer id;
    private Integer doTime;
    private Integer score;
    private List<ExamPaperQuestionCustomerAnswer> answerItems;
}
