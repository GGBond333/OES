package com.oes.gbloes.controller;

import com.oes.gbloes.controller.util.R;
import com.oes.gbloes.domain.Subject;
import com.oes.gbloes.service.ISubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/education/subject")
public class SubjectController {
    @Autowired
    ISubject iSubject;

    @GetMapping(value = {"/list/{level}/{pageIndex}/{pageSize}","/list/{pageIndex}/{pageSize}"})
    public R getSubjectPageList(@PathVariable(required = false) Integer level,@PathVariable Integer pageIndex,@PathVariable Integer pageSize){
        return new R(true,"查询成功",iSubject.getSubjectPage(level,pageIndex,pageSize));
    }

    @PostMapping
    public R addSubject(Subject subject){
        return new R(iSubject.addSubject(subject),"添加成功");
    }

    @PutMapping
    public R modifySubject(Subject subject){
        return new R(iSubject.modifySubject(subject),"修改成功");
    }

    @DeleteMapping("/delete/{id}")
    public R deleteSubject(@PathVariable Integer id){
        return new R(iSubject.deleteSubject(id));
    }



}
