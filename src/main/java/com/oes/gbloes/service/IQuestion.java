package com.oes.gbloes.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oes.gbloes.domain.Question;
import com.oes.gbloes.domain.Subject;
import com.oes.gbloes.viewmodel.admin.paper.PaperQuestionVM;
import com.oes.gbloes.viewmodel.admin.question.QuestionEditRequestVM;

import java.util.List;

public interface IQuestion extends IService<Question> {
    public void insertQuestion(QuestionEditRequestVM model);

    IPage<Question> getQuestionPge(Integer questionType,Integer subjectId,Integer level, Integer pageIndex, Integer pageSize);

    void deleteQuestion(Integer id);

    List<QuestionEditRequestVM> getQuestionItems(List<PaperQuestionVM> paperQuestionVMS);

    QuestionEditRequestVM getQuestionById(Integer id);

    void updateQuetion(QuestionEditRequestVM model);
}
