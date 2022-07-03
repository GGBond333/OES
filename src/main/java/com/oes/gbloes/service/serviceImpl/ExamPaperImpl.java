package com.oes.gbloes.service.serviceImpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oes.gbloes.dao.ExamPaperDao;
import com.oes.gbloes.dao.SubjectDao;
import com.oes.gbloes.dao.TextContentDao;
import com.oes.gbloes.domain.ExamPaper;
import com.oes.gbloes.domain.Subject;
import com.oes.gbloes.domain.TextContent;
import com.oes.gbloes.domain.User;
import com.oes.gbloes.domain.paper.PaperItemObject;
import com.oes.gbloes.domain.paper.PaperObject;
import com.oes.gbloes.service.IExamPaper;
import com.oes.gbloes.service.IQuestion;
import com.oes.gbloes.service.ITaskExam;
import com.oes.gbloes.utils.JsonUtil;
import com.oes.gbloes.utils.UserUtil;
import com.oes.gbloes.viewmodel.admin.paper.ExamPaperEditRequestVM;
import com.oes.gbloes.viewmodel.admin.paper.ExamPaperTitleItemVM;
import com.oes.gbloes.viewmodel.student.index.FixPaperVM;
import com.oes.gbloes.viewmodel.student.index.IndexVM;
import com.oes.gbloes.viewmodel.student.index.TimePaperVM;
import com.oes.gbloes.viewmodel.student.paper.ExamPaperRequestVM;
import com.oes.gbloes.viewmodel.student.paper.ExamPaperTitleItemRequestVM;
import net.sf.jsqlparser.statement.create.table.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Paper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ExamPaperImpl extends ServiceImpl<ExamPaperDao, ExamPaper> implements IExamPaper {
    @Autowired
    ExamPaperDao examPaperDao;
    @Autowired
    TextContentDao textContentDao;
    @Autowired
    SubjectDao subjectDao;
    @Autowired
    ITaskExam iTaskExam;
    @Autowired
    IQuestion iQuestion;
    @Override
    public void addExamPaper(ExamPaperEditRequestVM model) {
        Date date = DateUtil.date();
        ExamPaper examPaper = new ExamPaper();

        TextContent infoTextContent = new TextContent();
        infoTextContent.setCreateTime(date);

        AtomicInteger index = new AtomicInteger(1);

        List<PaperObject> paperObjectList = model.getItems().stream().map(i->{
            PaperObject paperObject = new PaperObject();
            paperObject.setName(i.getName());
            List<PaperItemObject> paperItemObjectList = i.getQuestionItems().stream().map(j->{
                PaperItemObject paperItemObject = new PaperItemObject();
                paperItemObject.setId(j.getId());
                paperItemObject.setItemOrder(index.getAndIncrement());
                return paperItemObject;
            }).collect(Collectors.toList());
            paperObject.setItems(paperItemObjectList);
            return paperObject;
        }).collect(Collectors.toList());

        infoTextContent.setContent(JsonUtil.toJsonStr(paperObjectList));
        textContentDao.insert(infoTextContent);
        examPaper.setFrameTextContentId(infoTextContent.getId());
        examPaper.setName(model.getName());
        examPaper.setSubjectId(model.getSubjectId());
        examPaper.setPaperType(model.getPaperType());
        examPaper.setGradeLevel(model.getGradeLevel());
        examPaper.setScore(model.getScore());
        examPaper.setQuestionCount(index.get());
        examPaper.setSuggestTime(model.getSuggestTime());
        examPaper.setLimitStartTime(model.getLimiteStartTime());
        examPaper.setLimitEndTime(model.getLimiteEndTime());
        examPaper.setCreateTime(date);
        examPaper.setDeleted(false);
        examPaper.setUsed(true);
        examPaperDao.insert(examPaper);
        examPaper.setPaperOrder(examPaper.getId());
        examPaperDao.updateById(examPaper);
    }

    @Override
    public IPage<ExamPaper> getExamPaperPage(Integer level, Integer subjectId, Integer pageIndex, Integer pageSize) {
        QueryWrapper<ExamPaper> examPaperQueryWrapper = new QueryWrapper<>();
        examPaperQueryWrapper.eq("deleted",0);
        examPaperQueryWrapper.eq("used",1);
        examPaperQueryWrapper.orderByAsc("paper_order");
        if(level!=null){
            examPaperQueryWrapper.eq("grade_level",level);
        }
        if(subjectId!=null){
            examPaperQueryWrapper.eq("subject_id",subjectId);
        }
        Page<ExamPaper> page = new Page<>(pageIndex,pageSize);

        IPage<ExamPaper> iPage = this.examPaperDao.selectPage(page,examPaperQueryWrapper);

        List<ExamPaper> examPaperList = iPage.getRecords();

        for(ExamPaper examPaper : examPaperList){
            Subject subject = subjectDao.selectById(examPaper.getSubjectId());
            examPaper.setSubjectName(subject.getName());
            examPaper.setGradeLevelName(subject.getLevelName());
        }
        iPage.setRecords(examPaperList);

        return iPage;
    }

    @Override
    public Boolean deleteExamPaper(Integer id) {
        ExamPaper examPaper = new ExamPaper();
        examPaper.setId(id);
        examPaper.setDeleted(true);
        return examPaperDao.updateById(examPaper)>0;
    }

    @Override
    public IndexVM getIndexInfo() {
        IndexVM indexVM = new IndexVM();
        User user = UserUtil.getUser();
        //固定试卷信息，取前5
        QueryWrapper<ExamPaper> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("paper_type",1).eq("grade_level",user.getUserLevel()).orderByDesc("create_time").last("limit 5");
        List<ExamPaper> examPaperList = examPaperDao.selectList(queryWrapper);
        List<FixPaperVM> fixPaperVMList = examPaperList.stream().map(i->{
            FixPaperVM fixPaperVM = new FixPaperVM();
            fixPaperVM.setId(i.getId());
            fixPaperVM.setName(i.getName());
            return fixPaperVM;
        }).collect(Collectors.toList());
        indexVM.setFixPaperVMList(fixPaperVMList);

        //时段试卷信息,取前5
        QueryWrapper<ExamPaper> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("paper_type",4).eq("grade_level",user.getUserLevel()).orderByDesc("create_time").last("limit 5");
        examPaperList = examPaperDao.selectList(queryWrapper1);
        List<TimePaperVM> timePaperVMList = examPaperList.stream().map(i->{
            TimePaperVM timePaperVM = new TimePaperVM();
            timePaperVM.setId(i.getId());
            timePaperVM.setName(i.getName());
            timePaperVM.setStartTime(i.getLimitStartTime());
            timePaperVM.setEndTime(i.getLimitEndTime());
            return timePaperVM;
        }).collect(Collectors.toList());
        indexVM.setTimePaperVMList(timePaperVMList);

        //任务试卷，取前10
        indexVM.setTaskPaperVMList(iTaskExam.getTaskPaperInfo());

        return indexVM;
    }

    @Override
    public IPage<ExamPaper> getExamPaperBySubjectAndPaperType(Integer subjectId, Integer paperType,Integer pageIndex,Integer pageSize) {
        QueryWrapper<ExamPaper> examPaperQueryWrapper = new QueryWrapper<>();
        examPaperQueryWrapper.eq("subject_Id",subjectId).eq("paper_type",paperType).orderByDesc("create_time");
        Page<ExamPaper> examPaperPage = new Page<>(pageIndex,pageSize);
        IPage<ExamPaper> iPage = examPaperDao.selectPage(examPaperPage,examPaperQueryWrapper);
        return iPage;
    }

    @Override
    public ExamPaperRequestVM getExamPaperRequestVM(Integer id) {
        ExamPaper examPaper = examPaperDao.selectById(id);
        ExamPaperRequestVM examPaperRequestVM = new ExamPaperRequestVM();

        examPaperRequestVM.setId(examPaper.getId());
        examPaperRequestVM.setName(examPaper.getName());
        examPaperRequestVM.setSubjectId(examPaper.getSubjectId());
        examPaperRequestVM.setPaperType(examPaper.getPaperType());
        examPaperRequestVM.setGradeLevel(examPaper.getGradeLevel());
        examPaperRequestVM.setScore(examPaper.getScore());
        examPaperRequestVM.setSuggestTime(examPaper.getSuggestTime());
        examPaperRequestVM.setLimiteStartTime(examPaper.getLimitStartTime());
        examPaperRequestVM.setLimiteEndTime(examPaper.getLimitEndTime());

        TextContent textContent = textContentDao.selectById(examPaper.getFrameTextContentId());

        List<ExamPaperTitleItemVM> examPaperTitleItemVMS = JSONUtil.toList(textContent.getContent(),ExamPaperTitleItemVM.class);

        List<ExamPaperTitleItemRequestVM> titleItems = examPaperTitleItemVMS.stream().map(i->{
            ExamPaperTitleItemRequestVM examPaperTitleItemRequestVM = new ExamPaperTitleItemRequestVM();
            examPaperTitleItemRequestVM.setName(i.getName());
            examPaperTitleItemRequestVM.setQuestionItems(iQuestion.getQuestionItems(i.getQuestionItems()));
            return examPaperTitleItemRequestVM;
        }).collect(Collectors.toList());
        examPaperRequestVM.setTitleItems(titleItems);
        return examPaperRequestVM;
    }
}
