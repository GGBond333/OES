package com.oes.gbloes.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName("t_exam_paper")
public class ExamPaper {
    @TableId
    Integer id;
    String name;
    Integer subjectId;
    Integer paperType;
    Integer gtadeLevel;
    Integer score;
    Integer questionCount;
    Integer suggestTime;
    Date limitStartTime;
    Date limiteEndTime;
    Integer frameTextContentId;
    Integer createUser;
    Date createTime;
    Boolean deleted;
    Integer taskExamId;
}
