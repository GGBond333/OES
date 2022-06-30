package com.oes.gbloes.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName("t_exam_paper_answer")
public class ExamPaperAnswer{
    @TableId
    private Integer id;
    private Integer examPaperId;
    private Integer paperName;
    private Integer subjectId;
    private Integer systemScore;
    private Integer userScore;
    private Integer paperScore;
    private Integer questionCorrect;
    private Integer questionCount;
    private Integer doTime;
    private Integer status;
    private Integer createUser;
    private Date createTime;
    private Integer taskExamId;

}
