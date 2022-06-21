package com.oes.gbloes.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName("t_task_exam")
public class TaskExam {
    @TableId
    Integer id;
    String title;
    Integer gradeLevel;
    Integer frameTextContentId;
    Integer createUser;
    Date createTime;
    Boolean deleted;
    String createUserName;
}
