package com.oes.gbloes.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oes.gbloes.domain.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionDao extends BaseMapper<Question> {

}
