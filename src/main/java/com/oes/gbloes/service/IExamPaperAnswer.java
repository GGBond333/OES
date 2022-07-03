package com.oes.gbloes.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oes.gbloes.domain.ExamPaperAnswer;
import com.oes.gbloes.viewmodel.student.answer.AnswerPaperVM;
import com.oes.gbloes.viewmodel.student.answer.ExamPaperSubmitVM;

public interface IExamPaperAnswer extends IService<ExamPaperAnswer> {

    Integer sumbitExamPaperAnswer(ExamPaperSubmitVM model);

    //查询考试记录
    IPage<ExamPaperAnswer> getExamPaperAnswerPage(Integer pageIndex,Integer pageSize);

    //查看答卷
    AnswerPaperVM getAnswerPaper(Integer id);
}
