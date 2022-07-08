package com.oes.gbloes.domain.question;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class QuestionObject {

    private String titleContent;

    private String analyze;

    private List<QuestionItemObject> questionItemObjects;

    private String correct;


}
