package com.oes.gbloes.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oes.gbloes.dao.SubjectDao;
import com.oes.gbloes.domain.Subject;
import com.oes.gbloes.service.ISubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectImpl extends ServiceImpl<SubjectDao, Subject> implements ISubject {

    @Autowired
    SubjectDao subjectDao;

    @Override
    public IPage<Subject> getSubjectPage(Integer level, Integer pageIndex, Integer pageSize) {
        QueryWrapper<Subject> subjectQueryWrapper = new QueryWrapper<>();
        if(level!=null){
            subjectQueryWrapper.eq("level",level);
        }
        //subjectQueryWrapper.orderByAsc("item_order");
        Page<Subject> page = new Page<>(pageIndex,pageSize);

        IPage<Subject> iPage = this.subjectDao.selectPage(page,subjectQueryWrapper);

        return iPage;
    }

    @Override
    public Boolean modifySubject(Subject subject) {
        Subject subject1 = new Subject();
        subject1.setId(subject.getId());
        subject1.setLevel(subject.getLevel());
        subject1.setLevelName(subject.getLevelName());

        return this.subjectDao.updateById(subject1)>0;
    }

    @Override
    public Boolean deleteSubject(Integer id) {

        return subjectDao.deleteById(id)>0;
    }

    @Override
    public Boolean addSubject(Subject subject) {
        subject.setDeleted(false);

        return this.subjectDao.insert(subject)>0;
    }

    @Override
    public Integer getSubjectLevel(Integer id) {
        Subject subject = this.subjectDao.selectById(id);

        return subject.getLevel();
    }
}
