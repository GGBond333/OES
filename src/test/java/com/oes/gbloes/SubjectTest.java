package com.oes.gbloes;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.oes.gbloes.domain.Subject;
import com.oes.gbloes.service.ISubject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SubjectTest {
    @Autowired
    ISubject iSubject;

    @Test
    void addSubject(){
        Subject subject = new Subject();
        subject.setName("语文");
        subject.setLevel(7);
        subject.setLevelName("七年级");
        iSubject.addSubject(subject);
    }

    @Test
    void modify(){
        Subject subject = new Subject();
        subject.setId(1);
        subject.setLevelName("九年级");
        iSubject.modifySubject(subject);

    }

    @Test
    void deleteSubject(){
        Subject subject = new Subject();
        subject.setId(1);
        iSubject.deleteSubject(subject.getId());

    }

    @Test
    void subjetQuery(){
        IPage<Subject> iPage = iSubject.getSubjectPage(7,1,10);
        List<Subject> list = iPage.getRecords();
        for (Subject subject:
             list) {
            System.out.println(subject);
        }
        System.out.println(iPage.getTotal());



    }
}
