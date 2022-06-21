package com.oes.gbloes.domain;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName("t_message_user")
public class MessageUser {
    @TableId
    Integer id;
    Integer messageId;
    Integer receiveUserId;
    String receiveUserNName;
    String receiveRealName;
    Boolean readed;
    Date creatTime;
    Date readTIme;

}
