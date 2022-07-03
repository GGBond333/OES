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
@TableName("t_message_user")
public class MessageUser {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer messageId;
    private Integer receiveUserId;
    private String receiveUserName;
    private String receiveRealName;
    private Boolean readed;
    private Date createTime;
    private Date readTime;

    @TableField(exist = false)
    private String title;
    @TableField(exist = false)
    private String content;
    @TableField(exist = false)
    private String sendUserName;
}
