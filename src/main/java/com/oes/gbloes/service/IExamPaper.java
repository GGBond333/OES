package com.oes.gbloes.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oes.gbloes.domain.ExamPaper;
import com.oes.gbloes.viewmodel.admin.paper.ExamPaperEditRequestVM;

public interface IExamPaper extends IService<ExamPaper> {
    public void addExamPaper(ExamPaperEditRequestVM model);

    public IPage<ExamPaper> getExamPaperPage(Integer level,Integer subjectId,Integer pageIndex,Integer pageSize);

    public Boolean deleteExamPaper(Integer id);
}
