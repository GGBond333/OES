package com.oes.gbloes.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName("t_user_envent_log")
public class UserEventLog {
    @TableId
    private Integer id;
    private Integer userId;
    private String userName;
    private String realName;
    private String content;
    private Date createTime;
}
