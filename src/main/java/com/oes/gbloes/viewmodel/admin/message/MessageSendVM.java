package com.oes.gbloes.viewmodel.admin.message;

import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
public class MessageSendVM {
    private String title;
    private String content;
    private List<Integer> receiveUserIds;
}
