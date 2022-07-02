package com.oes.gbloes.viewmodel.student.index;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class IndexVM {
    private List<TaskPaperVM> taskPaperVMList;
    private List<FixPaperVM> fixPaperVMList;
    private List<TimePaperVM> timePaperVMList;
}
