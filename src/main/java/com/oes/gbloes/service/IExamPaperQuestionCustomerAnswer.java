package com.oes.gbloes.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oes.gbloes.domain.ExamPaperQuestionCustomerAnswer;
import com.oes.gbloes.viewmodel.student.faultquetion.FaultQuestionInfoVM;

public interface IExamPaperQuestionCustomerAnswer extends IService<ExamPaperQuestionCustomerAnswer> {
    //错题分页
    IPage<ExamPaperQuestionCustomerAnswer> getFaultQuestion(Integer pageIndex,Integer pageSize);

    //通过错题id查询具体信息
    FaultQuestionInfoVM getFaultQuestionById(Integer id);
}
