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
    Integer id;
    Integer examPaperId;
    Integer paperName;
    Integer subjectId;
    Integer systemScore;
    Integer userScore;
    Integer paperScore;
    Integer questionCorrect;
    Integer questionCount;
    Integer doTime;
    Integer status;
    Integer createUser;
    Date createTime;
    Integer taskExamId;

}
