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
    private Integer id;
    private Integer messageId;
    private Integer receiveUserId;
    private String receiveUserNName;
    private String receiveRealName;
    private Boolean readed;
    private Date creatTime;
    private Date readTIme;

}
