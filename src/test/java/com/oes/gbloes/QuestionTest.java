package com.oes.gbloes;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.oes.gbloes.domain.Question;
import com.oes.gbloes.service.IQuestion;
import com.oes.gbloes.viewmodel.admin.question.QuestionEditItemVM;
import com.oes.gbloes.viewmodel.admin.question.QuestionEditRequestVM;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class QuestionTest {
    @Autowired
    IQuestion iQuestion;

    @Test
    public void addIQuestion(){
        List<QuestionEditItemVM> items = new ArrayList<>();
        QuestionEditItemVM item = new QuestionEditItemVM();
        item.setPrefix("A");
        item.setContent("铅笔");
        items.add(item);
        QuestionEditItemVM item1 = new QuestionEditItemVM();
        item1.setPrefix("B");
        item1.setContent("橡皮");
        items.add(item1);
        QuestionEditItemVM item2 = new QuestionEditItemVM();
        item2.setPrefix("C");
        item2.setContent("水");
        items.add(item2);
        QuestionEditItemVM item3 = new QuestionEditItemVM();
        item3.setPrefix("D");
        item3.setContent("尺子");
        items.add(item3);

        QuestionEditRequestVM model = new QuestionEditRequestVM();

        model.setQuestionType(1);
        model.setSubjectId(3);
        model.setTitle("下列哪个不是文具");
        model.setGradeLevel(2);
        model.setItems(items);
        model.setAnalyze("常识");
        model.setCorrect("C");
        model.setScore(3);
        model.setDifficult(1);
        iQuestion.insertQuestion(model);

    }

    @Test
    public void selectPage(){
        IPage<Question> iPage = iQuestion.getQuestionPge(null,null,null,1,10);
        List<Question> list = iPage.getRecords();
        for(Question question : list){
            System.out.println(question);
        }
    }

    @Test
    public void deleteQuestion(){
        iQuestion.deleteQuestion(9);
    }
}
