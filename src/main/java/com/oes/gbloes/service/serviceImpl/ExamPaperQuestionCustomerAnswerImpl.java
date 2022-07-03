package com.oes.gbloes.service.serviceImpl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oes.gbloes.dao.ExamPaperQuestionCustomerAnswerDao;
import com.oes.gbloes.dao.QuestionDao;
import com.oes.gbloes.dao.TextContentDao;
import com.oes.gbloes.domain.ExamPaperQuestionCustomerAnswer;
import com.oes.gbloes.domain.Question;
import com.oes.gbloes.domain.TextContent;
import com.oes.gbloes.domain.User;
import com.oes.gbloes.service.IExamPaperQuestionCustomerAnswer;
import com.oes.gbloes.service.ISubject;
import com.oes.gbloes.utils.UserUtil;
import com.oes.gbloes.viewmodel.admin.question.QuestionEditItemVM;
import com.oes.gbloes.viewmodel.admin.question.QuestionEditRequestVM;
import com.oes.gbloes.viewmodel.student.answer.ExamPaperSubmitItemVM;
import com.oes.gbloes.viewmodel.student.faultquetion.FaultQuestionInfoVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamPaperQuestionCustomerAnswerImpl extends ServiceImpl<ExamPaperQuestionCustomerAnswerDao, ExamPaperQuestionCustomerAnswer> implements IExamPaperQuestionCustomerAnswer {
    @Autowired
    ExamPaperQuestionCustomerAnswerDao examPaperQuestionCustomerAnswerDao;
    @Autowired
    TextContentDao textContentDao;
    @Autowired
    ISubject iSubject;
    @Autowired
    QuestionDao questionDao;
    @Override
    public IPage<ExamPaperQuestionCustomerAnswer> getFaultQuestion(Integer pageIndex, Integer pageSize) {
        User user = UserUtil.getUser();
        QueryWrapper<ExamPaperQuestionCustomerAnswer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("create_user",user.getId()).eq("do_right",0);
        Page<ExamPaperQuestionCustomerAnswer> examPaperQuestionCustomerAnswerPage = new Page<>(pageIndex,pageSize);
        IPage<ExamPaperQuestionCustomerAnswer> iPage = examPaperQuestionCustomerAnswerDao.selectPage(examPaperQuestionCustomerAnswerPage,queryWrapper);
        List<ExamPaperQuestionCustomerAnswer> examPaperQuestionCustomerAnswerList = iPage.getRecords();
        for (ExamPaperQuestionCustomerAnswer examPaperQuestionCustomerAnswer : examPaperQuestionCustomerAnswerList){
            TextContent textContent = textContentDao.selectById(examPaperQuestionCustomerAnswer.getQuestionTextContentId());
            JSONObject jsonObject = JSONUtil.parseObj(textContent.getContent());
            examPaperQuestionCustomerAnswer.setTitleContent(jsonObject.getStr("titleContent"));
            examPaperQuestionCustomerAnswer.setSubjectName(iSubject.getSubjectById(examPaperQuestionCustomerAnswer.getSubjectId()).getName());
        }
        iPage.setRecords(examPaperQuestionCustomerAnswerList);
        return iPage;
    }

    @Override
    public FaultQuestionInfoVM getFaultQuestionById(Integer id) {

        ExamPaperQuestionCustomerAnswer examPaperQuestionCustomerAnswer = examPaperQuestionCustomerAnswerDao.selectById(id);

        //设置原题信息
        QuestionEditRequestVM questionEditRequestVM = new QuestionEditRequestVM();
        Question question = questionDao.selectById(examPaperQuestionCustomerAnswer.getQuestionId());
        TextContent textContent = textContentDao.selectById(question.getId());
        JSONObject jsonObject = JSONUtil.parseObj(textContent.getContent());
        List<QuestionEditItemVM> items = JSONUtil.toList(jsonObject.getStr("questionItemObjects"),QuestionEditItemVM.class);
        questionEditRequestVM.setId(question.getId());
        questionEditRequestVM.setQuestionType(question.getQuestionType());
        questionEditRequestVM.setSubjectId(question.getSubjectId());
        questionEditRequestVM.setTitle(jsonObject.getStr("titleContent"));
        questionEditRequestVM.setGradeLevel(question.getGradeLevel());
        questionEditRequestVM.setItems(items);
        questionEditRequestVM.setAnalyze(jsonObject.getStr("analyze"));
        questionEditRequestVM.setCorrect(question.getCorrect());
        questionEditRequestVM.setScore(question.getScore());
        questionEditRequestVM.setDifficult(question.getDifficult());
        questionEditRequestVM.setItemOrder(examPaperQuestionCustomerAnswer.getItemOrder());
        //设置答题信息
        ExamPaperSubmitItemVM examPaperSubmitItemVM = new ExamPaperSubmitItemVM();
        examPaperSubmitItemVM.setQuestionId(examPaperQuestionCustomerAnswer.getQuestionId());
        examPaperSubmitItemVM.setContent(examPaperQuestionCustomerAnswer.getAnswer());
        if(examPaperQuestionCustomerAnswer.getTextContentId()!=null){
            TextContent textContent1 = textContentDao.selectById(examPaperQuestionCustomerAnswer.getTextContentId());
            JSONArray array = JSONUtil.parseArray(textContent1.getContent());
            examPaperSubmitItemVM.setContenArray(array.toList(String.class));
        }
        examPaperSubmitItemVM.setItemOrder(examPaperQuestionCustomerAnswer.getItemOrder());

        //设置错误详细类
        FaultQuestionInfoVM faultQuestionInfoVM = new FaultQuestionInfoVM();
        faultQuestionInfoVM.setQuestionVM(questionEditRequestVM);
        faultQuestionInfoVM.setQuestionAnswerVM(examPaperSubmitItemVM);
        return faultQuestionInfoVM;
    }
}
