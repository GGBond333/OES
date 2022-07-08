package com.oes.gbloes.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oes.gbloes.domain.Subject;

import java.util.List;

public interface ISubject extends IService<Subject> {

    //学科分页并查询
    IPage<Subject> getSubjectPage(Integer level, Integer pageIndex, Integer pageSize);

    //学科修改分页
    Boolean modifySubject(Subject subject);

    /**
     *
     * @param id
     * @return
     */
    Boolean deleteSubject(Integer id);

    //用户添加
    Boolean addSubject(Subject subject);

    Integer getSubjectLevel(Integer id);

    Subject getSubjectById(Integer id);

    //通过学生年级返回该年级的学科
    List<Subject> getSubjects();

    List<Subject> getSubjectsByLevel(Integer level);
}
