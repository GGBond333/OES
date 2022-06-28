package com.oes.gbloes.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName("t_text_content")
public class TextContent {
    @TableId(type = IdType.AUTO)
    Integer id;
    String content;
    Date createTime;

}
