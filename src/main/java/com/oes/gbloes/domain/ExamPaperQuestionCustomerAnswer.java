package com.oes.gbloes.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString
@TableName("t_exam_paper_question_customer_answer")
public class ExamPaperQuestionCustomerAnswer {
    @TableId(type = IdType.AUTO)
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
    @TableField(exist = false)
    private List<String> contentArray;
    @TableField(exist = false)
    private String titleContent;
    @TableField(exist = false)
    private String subjectName;

}
