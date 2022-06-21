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
    Integer id;
    String title;
    String content;
    Date dateTime;
    Integer sendUserId;
    String sendUserName;
    String sendRealName;
    Integer receiveUserCount;
    Integer redCount;
}
