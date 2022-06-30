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
    private Integer id;
    private String token;
    private Integer userId;
    private String wxOpenId;
    private Date createTime;
    private Date endTime;
    private String userName;
}
