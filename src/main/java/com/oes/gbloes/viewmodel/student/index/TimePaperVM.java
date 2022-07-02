package com.oes.gbloes.viewmodel.student.index;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class TimePaperVM {
    private Integer id;
    private String name;
    private Date startTime;
    private Date endTime;
}
