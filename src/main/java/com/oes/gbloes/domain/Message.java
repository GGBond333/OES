package com.oes.gbloes.domain;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName("t_message")
public class Message {
    @TableId
    private Integer id;
    private String title;
    private String content;
    private Date dateTime;
    private Integer sendUserId;
    private String sendUserName;
    private String sendRealName;
    private Integer receiveUserCount;
    private Integer redCount;
}
