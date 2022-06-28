package com.oes.gbloes.viewmodel.admin.question;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class QuestionEditRequestVM {
    private Integer id;

    private Integer questionType;

    private Integer subjectId;
    //题干
    private String title;
    //年级
    private Integer gradeLevel;

    private List<QuestionEditItemVM> items;

    private String analyze;

    private List<String> correctArray;

    private String correct;

    private Integer score;

    private Integer difficult;

}
