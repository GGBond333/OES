package com.oes.gbloes.viewmodel.admin.paper;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ExamPaperTitleItemVM {
    private String name;
    private List<PaperQuestionVM> questionItms;
}
