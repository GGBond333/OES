package com.oes.gbloes.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("t_subject")
public class Subject {
    @TableId(type = IdType.AUTO)
    Integer id;
    String name;
    Integer level;
    String levelName;
    Integer itemOrder;
    Boolean deleted;
}
