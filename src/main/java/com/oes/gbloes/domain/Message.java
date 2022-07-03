package com.oes.gbloes.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

@Data
@ToString
@TableName("t_message")
public class Message {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String content;
    private Date createTime;
    private Integer sendUserId;
    private String sendUserName;
    private String sendRealName;
    private Integer receiveUserCount;
    private Integer readCount;

    @TableField(exist = false)
    private List<String> userNameList;
    @TableField(exist = false)
    private Boolean readed;
}
