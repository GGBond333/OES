package com.oes.gbloes.service.serviceImpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oes.gbloes.dao.*;
import com.oes.gbloes.domain.*;
import com.oes.gbloes.service.IExamPaper;
import com.oes.gbloes.service.IExamPaperAnswer;
import com.oes.gbloes.service.ISubject;
import com.oes.gbloes.utils.JsonUtil;
import com.oes.gbloes.utils.UserUtil;
import com.oes.gbloes.viewmodel.student.answer.AnswerPaperVM;
import com.oes.gbloes.viewmodel.student.answer.AnswerVM;
import com.oes.gbloes.viewmodel.student.answer.ExamPaperSubmitVM;
import com.oes.gbloes.viewmodel.student.paper.ExamPaperRequestVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamPaperAnswerImpl extends ServiceImpl<ExamPaperAnswerDao, ExamPaperAnswer> implements IExamPaperAnswer {
    @Autowired
    ExamPaperAnswerDao examPaperAnswerDao;
    @Autowired
    ExamPaperDao examPaperDao;
    @Autowired
    ExamPaperQuestionCustomerAnswerDao examPaperQuestionCustomerAnswerDao;
    @Autowired
    QuestionDao questionDao;
    @Autowired
    TextContentDao textContentDao;
    @Autowired
    ISubject iSubject;
    @Autowired
    IExamPaper iExamPaper;
    //题目数量
    private Integer questionCount=0;
    //正确数量
    private Integer questionCorrect=0;
    //选择题判断题得分
    private Integer systemScore=0;

    @Override
    public Integer sumbitExamPaperAnswer(ExamPaperSubmitVM model){
        User user = UserUtil.getUser();
        this.questionCount=0;
        this.questionCorrect=0;
        this.systemScore=0;
        //插入答卷信息
        ExamPaper examPaper = examPaperDao.selectById(model.getId());
        ExamPaperAnswer examPaperAnswer = new ExamPaperAnswer();
        examPaperAnswer.setExamPaperId(examPaper.getId());
        examPaperAnswer.setPaperName(examPaper.getName());
        examPaperAnswer.setPaperType(examPaper.getPaperType());
        examPaperAnswer.setSubjectId(examPaper.getSubjectId());
        examPaperAnswer.setPaperScore(examPaper.getScore());
        examPaperAnswer.setDoTime(model.getDoTime());
        examPaperAnswer.setStatus(1);
        examPaperAnswer.setCreateTime(DateUtil.date());
        examPaperAnswer.setCreateUser(user.getId());
        examPaperAnswerDao.insert(examPaperAnswer);

        //插入答卷题目信息
        List<ExamPaperQuestionCustomerAnswer> examPaperQuestionCustomerAnswerList = model.getAnswerItems().stream().map(i->{
            this.questionCount += 1;
            ExamPaperQuestionCustomerAnswer examPaperQuestionCustomerAnswer = new ExamPaperQuestionCustomerAnswer();
            Question question = questionDao.selectById(i.getQuestionId());
            examPaperQuestionCustomerAnswer.setQuestionId(question.getId());
            examPaperQuestionCustomerAnswer.setExamPaperId(examPaper.getId());
            examPaperQuestionCustomerAnswer.setExamPaperAnswerId(examPaperAnswer.getId());
            examPaperQuestionCustomerAnswer.setQuestionType(question.getQuestionType());
            examPaperQuestionCustomerAnswer.setSubjectId(question.getSubjectId());
            examPaperQuestionCustomerAnswer.setQuestionScore(question.getScore());
            examPaperQuestionCustomerAnswer.setQuestionTextContentId(question.getInfoTextContentId());
            examPaperQuestionCustomerAnswer.setAnswer(i.getContent());
            if(i.getContenArray().size()>0){
                TextContent textContent = new TextContent();
                textContent.setContent(JsonUtil.toJsonStr(i.getContenArray()));
                textContent.setCreateTime(DateUtil.date());
                textContentDao.insert(textContent);
                examPaperQuestionCustomerAnswer.setTextContentId(textContent.getId());
            }
            if(question.getCorrect().equals(i.getContent())&&!i.getContent().equals("")){
                examPaperQuestionCustomerAnswer.setDoRight(true);
                examPaperQuestionCustomerAnswer.setCustomerScore(question.getScore());
                this.systemScore += question.getScore();
                this.questionCorrect +=1;
            }else {
                examPaperQuestionCustomerAnswer.setCustomerScore(0);
            }
            examPaperQuestionCustomerAnswer.setCreateTime(DateUtil.date());
            examPaperQuestionCustomerAnswer.setItemOrder(i.getItemOrder());
            examPaperQuestionCustomerAnswer.setCreateUser(user.getId());
            examPaperQuestionCustomerAnswerDao.insert(examPaperQuestionCustomerAnswer);
            return examPaperQuestionCustomerAnswer;
        }).collect(Collectors.toList());
        //更新答卷信息
        examPaperAnswer.setSystemScore(this.systemScore);
        examPaperAnswer.setUserScore(this.systemScore);
        examPaperAnswer.setQuestionCorrect(this.questionCorrect);
        examPaperAnswer.setQuestionCount(this.questionCount);
        examPaperAnswerDao.updateById(examPaperAnswer);
        return systemScore;
    }

    @Override
    public IPage<ExamPaperAnswer> getExamPaperAnswerPage(Integer pageIndex, Integer pageSize) {
        QueryWrapper<ExamPaperAnswer> examPaperAnswerQueryWrapper = new QueryWrapper<>();
        Page<ExamPaperAnswer> paperAnswerPage = new Page<>(pageIndex,pageSize);
        IPage<ExamPaperAnswer> iPage = examPaperAnswerDao.selectPage(paperAnswerPage,examPaperAnswerQueryWrapper);
        List<ExamPaperAnswer> examPaperAnswerList = iPage.getRecords();
        for (ExamPaperAnswer examPaperAnswer : examPaperAnswerList){
            examPaperAnswer.setSubjectName(iSubject.getSubjectById(examPaperAnswer.getSubjectId()).getName());
        }
        iPage.setRecords(examPaperAnswerList);
        return iPage;
    }

    @Override
    public AnswerPaperVM getAnswerPaper(Integer id) {
        ExamPaperAnswer examPaperAnswer = examPaperAnswerDao.selectById(id);
        ExamPaperRequestVM examPaperRequestVM = iExamPaper.getExamPaperRequestVM(examPaperAnswer.getExamPaperId());
        AnswerPaperVM answerPaperVM = new AnswerPaperVM();

        answerPaperVM.setPaper(examPaperRequestVM);

        AnswerVM answerVM = new AnswerVM();
        answerVM.setId(examPaperAnswer.getId());
        answerVM.setDoTime(examPaperAnswer.getDoTime());
        answerVM.setScore(examPaperAnswer.getUserScore());
        QueryWrapper<ExamPaperQuestionCustomerAnswer> examPaperQuestionCustomerAnswerQueryWrapper = new QueryWrapper<>();
        examPaperQuestionCustomerAnswerQueryWrapper.eq("exam_paper_answer_id",examPaperAnswer.getId()).orderByAsc("item_order");
        List<ExamPaperQuestionCustomerAnswer> answerItems = examPaperQuestionCustomerAnswerDao.selectList(examPaperQuestionCustomerAnswerQueryWrapper);
        for(ExamPaperQuestionCustomerAnswer examPaperQuestionCustomerAnswer : answerItems){
            if(examPaperQuestionCustomerAnswer.getTextContentId()!=null){
                TextContent textContent = textContentDao.selectById(examPaperQuestionCustomerAnswer.getTextContentId());
                JSONArray array = JSONUtil.parseArray(textContent.getContent());
                examPaperQuestionCustomerAnswer.setContentArray(array.toList(String.class));
            }

        }
        answerVM.setAnswerItems(answerItems);
        answerPaperVM.setAnswer(answerVM);
        return answerPaperVM;
    }
}
