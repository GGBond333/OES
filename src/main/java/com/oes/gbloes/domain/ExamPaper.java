package com.oes.gbloes.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.lang.reflect.Type;
import java.util.Date;

@Data
@ToString
@TableName("t_exam_paper")
public class ExamPaper {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer subjectId;
    private Integer paperType;
    private Integer gradeLevel;
    private Integer score;
    private Integer questionCount;
    private Integer suggestTime;
    private Date limitStartTime;
    private Date limitEndTime;
    private Integer frameTextContentId;
    private Integer createUser;
    private Date createTime;
    private Boolean deleted;
    private Integer taskExamId;
    private Boolean used;
    private Integer paperOrder;
    @TableField(exist = false)
    private String subjectName;
    @TableField(exist = false)
    private String gradeLevelName;
}
