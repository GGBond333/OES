package com.oes.gbloes.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName("t_question")
public class Question {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer questionType;
    private Integer subjectId;
    private Integer score;
    private Integer gradeLevel;
    private Integer difficult;
    private String correct;
    private Integer infoTextContentId;
    private Integer createUser;
    private Integer status;
    private Date createTime;
    private Boolean deleted;

    @TableField(exist = false)
    private String title;
    @TableField(exist = false)
    private String subjectName;
    @TableField(exist = false)
    private String gradeLevelName;
}
