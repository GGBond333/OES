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
    private Integer id;
    private String title;
    private Integer gradeLevel;
    private Integer frameTextContentId;
    private Integer createUser;
    private Date createTime;
    private Boolean deleted;
    private String createUserName;
}
