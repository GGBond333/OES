package com.oes.gbloes.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName("t_exam_paper_question_customer_answer")
public class ExamPaperQuestionCustomerAnswer {
    @TableId
    Integer id;
    Integer questionId;
    Integer examPaperId;
    Integer examPaperAnswerId;
    Integer questionType;
    Integer subjectId;
    Integer customerScore;
    Integer questionScore;
    Integer questionTextContentId;
    String answer;
    Integer textContentId;
    boolean doRight;
    Integer createUser;
    Date createTime;
    Integer itemOrder;


}
