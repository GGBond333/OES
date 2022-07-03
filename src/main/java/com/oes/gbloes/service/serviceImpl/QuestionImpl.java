package com.oes.gbloes.service.serviceImpl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oes.gbloes.dao.QuestionDao;
import com.oes.gbloes.dao.SubjectDao;
import com.oes.gbloes.dao.TextContentDao;
import com.oes.gbloes.domain.Question;
import com.oes.gbloes.domain.Subject;
import com.oes.gbloes.domain.TextContent;
import com.oes.gbloes.domain.question.QuestionItemObject;
import com.oes.gbloes.domain.question.QuestionObject;
import com.oes.gbloes.service.IQuestion;
import com.oes.gbloes.service.ISubject;
import com.oes.gbloes.utils.ExamUtil;
import com.oes.gbloes.utils.JsonUtil;
import com.oes.gbloes.viewmodel.admin.paper.PaperQuestionVM;
import com.oes.gbloes.viewmodel.admin.question.QuestionEditItemVM;
import com.oes.gbloes.viewmodel.admin.question.QuestionEditRequestVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionImpl extends ServiceImpl<QuestionDao, Question> implements IQuestion {

    @Autowired
    ISubject iSubject;
    @Autowired
    QuestionDao questionDao;
    @Autowired
    TextContentDao textContentDao;
    @Autowired
    SubjectDao subjectDao;


    @Override
    public void insertQuestion(QuestionEditRequestVM model) {
        Calendar now = Calendar.getInstance();
        Question question = new Question();
        question.setGradeLevel(iSubject.getSubjectLevel(model.getSubjectId()));

        TextContent infoTextContent = new TextContent();
        infoTextContent.setCreateTime(now.getTime());

        //设置 插入到文本数据里面
        List<QuestionItemObject> itemObjects =model.getItems().stream().map(i ->{
            QuestionItemObject item = new QuestionItemObject();
            item.setPrefix(i.getPrefix());
            item.setContent(i.getContent());
            item.setSrore(ExamUtil.scoreFromVM(i.getSrore()));
            item.setItemUuid(i.getItemUuid());
            return item;
        }).collect(Collectors.toList());

        QuestionObject questionObject = new QuestionObject();
        questionObject.setTitleContent(model.getTitle());
        questionObject.setAnalyze(model.getAnalyze());
        questionObject.setCorrect(model.getCorrect());
        questionObject.setQuestionItemObjectList(itemObjects);
        infoTextContent.setContent(JsonUtil.toJsonStr(questionObject));
        textContentDao.insert(infoTextContent);
        Integer textContentId =  infoTextContent.getId();

        question.setQuestionType(model.getQuestionType());
        question.setSubjectId(model.getSubjectId());
        question.setScore(model.getScore());
        question.setDifficult(model.getDifficult());
        question.setCorrect(model.getCorrect());
        question.setInfoTextContentId(textContentId);
        question.setStatus(1);
        question.setCreateTime(now.getTime());
        question.setDeleted(false);
        questionDao.insert(question);
    }

    @Override
    public IPage<Question> getQuestionPge(Integer questionType, Integer subjectId, Integer level, Integer pageIndex, Integer pageSize) {
        QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<>();
        questionQueryWrapper.eq("deleted",0);
        if(questionType!=null){
            questionQueryWrapper.eq("question_type",questionType);
        }
        if(subjectId!=null){
            questionQueryWrapper.eq("subject_id",subjectId);
        }
        if(level!=null){
            questionQueryWrapper.eq("grade_level",level);
        }
        Page<Question> page = new Page<>(pageIndex,pageSize);

        IPage<Question> iPage = this.questionDao.selectPage(page,questionQueryWrapper);

        List<Question> questionList = iPage.getRecords();

        for(Question question : questionList){
            Subject subject = subjectDao.selectById(question.getSubjectId());
            question.setSubjectName(subject.getName());
            question.setGradeLevelName(subject.getLevelName());

            TextContent textContent = textContentDao.selectById(question.getInfoTextContentId());
            JSONObject jsonObject = JSONUtil.parseObj(textContent.getContent());
            String title =  jsonObject.getStr("titleContent");
            System.out.println("============title:"+title);
            System.out.println("============="+jsonObject.getStr("titleContent"));
            question.setTitle(title);
        }
        iPage.setRecords(questionList);
        return iPage;
    }

    @Override
    public void deleteQuestion(Integer id) {
        Question question = new Question();
        question.setId(id);
        question.setDeleted(true);
        questionDao.updateById(question);


    }

    //获取答题的每个部分的题目
    @Override
    public List<QuestionEditRequestVM> getQuestionItems(List<PaperQuestionVM> paperQuestionVMS) {
        List<QuestionEditRequestVM> questionEditRequestVMList = paperQuestionVMS.stream().map(i->{
            QuestionEditRequestVM questionEditRequestVM = new QuestionEditRequestVM();
            Question question = questionDao.selectById(i.getId());
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
            questionEditRequestVM.setItemOrder(i.getItemOrder());
            return questionEditRequestVM;
        }).collect(Collectors.toList());


        return questionEditRequestVMList;
    }
}
