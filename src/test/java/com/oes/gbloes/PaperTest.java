package com.oes.gbloes;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.oes.gbloes.dao.ExamPaperDao;
import com.oes.gbloes.domain.ExamPaper;
import com.oes.gbloes.service.IExamPaper;
import com.oes.gbloes.service.IQuestion;
import com.oes.gbloes.viewmodel.admin.paper.ExamPaperEditRequestVM;
import com.oes.gbloes.viewmodel.admin.paper.ExamPaperTitleItemVM;
import com.oes.gbloes.viewmodel.admin.paper.PaperQuestionVM;
import com.oes.gbloes.viewmodel.student.index.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PaperTest {
    @Autowired
    IExamPaper iExamPaper;

    @Test
    public void addPaper(){
        List<ExamPaperTitleItemVM> examPaperTitleItemVMS = new ArrayList<>();

        List<PaperQuestionVM> paperQuestionVMList = new ArrayList<>();
        PaperQuestionVM paperQuestionVM1 = new PaperQuestionVM();
        paperQuestionVM1.setId(1);
        paperQuestionVMList.add(paperQuestionVM1);

        ExamPaperTitleItemVM examPaperTitleItemVM = new ExamPaperTitleItemVM();
        examPaperTitleItemVM.setName("生物选择题");
        examPaperTitleItemVM.setQuestionItms(paperQuestionVMList);
        examPaperTitleItemVMS.add(examPaperTitleItemVM);

        List<PaperQuestionVM> paperQuestionVMList1 = new ArrayList<>();
        PaperQuestionVM paperQuestionVM2 = new PaperQuestionVM();
        paperQuestionVM2.setId(2);
        paperQuestionVMList1.add(paperQuestionVM2);

        ExamPaperTitleItemVM examPaperTitleItemVM2 = new ExamPaperTitleItemVM();
        examPaperTitleItemVM2.setName("生物填空题");
        examPaperTitleItemVM2.setQuestionItms(paperQuestionVMList1);
        examPaperTitleItemVMS.add(examPaperTitleItemVM2);

        ExamPaperEditRequestVM model = new ExamPaperEditRequestVM();
        model.setItems(examPaperTitleItemVMS);
        model.setName("第一次月考");
        model.setSubjectId(5);
        model.setGradeLevel(9);
        model.setScore(100);
        model.setPaperType(1);
        model.setSuggestTime(100);
        String startTime = "2022-07-08 08:00:00";
        String endTime = "2022-07-08 09:40:00";
        model.setLimiteStartTime(DateUtil.parse(startTime));
        model.setLimiteEndTime(DateUtil.parse(endTime));
        iExamPaper.addExamPaper(model);

    }

    @Test
    public void getPaperPage(){
        IPage<ExamPaper> iPage = iExamPaper.getExamPaperPage(null,null,1,10);
        List<ExamPaper> examPaperList = iPage.getRecords();
        for(ExamPaper examPaper : examPaperList){
            System.out.println(examPaper);
        }
    }

    //学生首页测试
    @Test
    public void getIndex(){
        IndexVM indexVM = iExamPaper.getIndexInfo();
        List<FixPaperVM> fixPaperVMList = indexVM.getFixPaperVMList();
        System.out.println("FIXPAPER");
        for(FixPaperVM fixPaperVM : fixPaperVMList){
            System.out.println(fixPaperVM);
        }
        System.out.println("TIMEPAPER");
        List<TimePaperVM> timePaperVMList = indexVM.getTimePaperVMList();
        for (TimePaperVM timePaperVM : timePaperVMList){
            System.out.println(timePaperVM);
        }
        System.out.println("TASKPAPER");
        List<TaskPaperVM> taskPaperVMList = indexVM.getTaskPaperVMList();
        for (TaskPaperVM taskPaperVM : taskPaperVMList){
            List<TaskPaperInfoVM> taskPaperInfoVMList = taskPaperVM.getTaskPapers();
            System.out.println(taskPaperInfoVMList);
            for (TaskPaperInfoVM taskPaperInfoVM : taskPaperInfoVMList){
                System.out.println(taskPaperInfoVM);
            }

        }
    }
}
