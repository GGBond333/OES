package com.oes.gbloes.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName("t_task_exam_customer_answer")
public class TaskExamCustomerAnswer {
    @TableId
    private Integer id;
    private Integer taskExamId;
    private Integer createUser;
    private Date createTime;
    private Integer textContentId;
}
