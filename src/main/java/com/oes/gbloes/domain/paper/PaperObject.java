package com.oes.gbloes.domain.paper;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class PaperObject {
    private String name;
    public List<PaperItemObject> items;
}
