package com.oes.gbloes.domain.question;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class QuestionItemObject {

    private String prefix;

    private String content;

    private Integer srore;

    private String itemUuid;

}
