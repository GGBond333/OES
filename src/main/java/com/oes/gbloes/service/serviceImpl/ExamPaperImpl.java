package com.oes.gbloes.service.serviceImpl;

import cn.hutool.core.date.DateUtil;
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
import com.oes.gbloes.domain.paper.PaperItemObject;
import com.oes.gbloes.domain.paper.PaperObject;
import com.oes.gbloes.service.IExamPaper;
import com.oes.gbloes.utils.JsonUtil;
import com.oes.gbloes.viewmodel.admin.paper.ExamPaperEditRequestVM;
import com.oes.gbloes.viewmodel.student.index.FixPaperVM;
import com.oes.gbloes.viewmodel.student.index.IndexVM;
import com.oes.gbloes.viewmodel.student.index.TimePaperVM;
import net.sf.jsqlparser.statement.create.table.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Paper;
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
            List<PaperItemObject> paperItemObjectList = i.getQuestionItms().stream().map(j->{
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

        //固定试卷信息，取前5
        QueryWrapper<ExamPaper> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("paper_type",1).orderByDesc("create_time").last("limit 5");
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
        queryWrapper1.eq("paper_type",4).orderByDesc("create_time").last("limit 5");
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
        QueryWrapper<ExamPaper> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("paper_type",6).orderByDesc("create_time").last("limit 10");


        return null;
    }
}
