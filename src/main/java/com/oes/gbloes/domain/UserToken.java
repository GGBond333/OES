package com.oes.gbloes.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName("t_user_token")
public class UserToken {
    @TableId
    Integer id;
    String token;
    Integer userId;
    String wxOpenId;
    Date createTime;
    Date endTime;
    String userName;
}
