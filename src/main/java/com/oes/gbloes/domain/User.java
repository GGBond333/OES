package com.oes.gbloes.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName("t_user")
public class User {
    @TableId(type = IdType.AUTO)
    Integer id;
    String userUuid;
    String userName;
    String password;
    /**
     * 真实姓名
     */
    private String realName;

    private Integer age;

    /**
     * 1.男 2女
     */
    private Integer sex;

    private Date birthDay;

    /**
     * 学生年级(1-12)
     */
    private Integer userLevel;

    private String phone;

    /**
     * 1.学生  3.管理员
     */
    private Integer role;

    /**
     * 1.启用 2禁用
     */
    private Integer status;

    /**
     * 头像地址
     */
    private String imagePath;

    private Date createTime;

    private Date modifyTime;

    private Date lastActiveTime;

    /**
     * 是否删除
     */
    private Boolean deleted;

    private String wxOpenId;
}
