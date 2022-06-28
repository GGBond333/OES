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
    Integer id;
    Integer questionType;
    Integer subjectId;
    Integer score;
    Integer gradeLevel;
    Integer difficult;
    String correct;
    Integer infoTextContentId;
    Integer createUser;
    Integer status;
    Date createTime;
    Boolean deleted;

    @TableField(exist = false)
    String title;
    @TableField(exist = false)
    String subjectName;
    @TableField(exist = false)
    String gradeLevelName;
}
