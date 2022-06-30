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
    private Integer id;
    private Integer questionId;
    private Integer examPaperId;
    private Integer examPaperAnswerId;
    private Integer questionType;
    private Integer subjectId;
    private Integer customerScore;
    private Integer questionScore;
    private Integer questionTextContentId;
    private String answer;
    private Integer textContentId;
    private boolean doRight;
    private Integer createUser;
    private Date createTime;
    private Integer itemOrder;


}
