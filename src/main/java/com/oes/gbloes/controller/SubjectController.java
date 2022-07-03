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
        return R.ok(iSubject.getSubjectPage(level,pageIndex,pageSize));
    }

    @PostMapping
    public R addSubject(Subject subject){
        return R.ok(iSubject.addSubject(subject));
    }

    @PutMapping
    public R modifySubject(Subject subject){
        return R.ok(iSubject.modifySubject(subject));
    }

    @DeleteMapping("/delete/{id}")
    public R deleteSubject(@PathVariable Integer id){
        return R.ok(iSubject.deleteSubject(id));
    }

    @GetMapping("selete/{id}")
    public R seleteSubjectById(@PathVariable Integer id){
        return R.ok(iSubject.getSubjectById(id));
    }

}
