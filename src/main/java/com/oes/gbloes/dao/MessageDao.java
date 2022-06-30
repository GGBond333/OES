package com.oes.gbloes.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oes.gbloes.domain.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageDao extends BaseMapper<Message> {
}
