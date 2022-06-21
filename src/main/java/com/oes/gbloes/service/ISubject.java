package com.oes.gbloes.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oes.gbloes.domain.Subject;

public interface ISubject extends IService<Subject> {

    //ѧ�Ʒ�ҳ����ѯ
    IPage<Subject> getSubjectPage(Integer level, Integer pageIndex, Integer pageSize);

    //ѧ���޸ķ�ҳ
    Boolean modifySubject(Subject subject);

    //�û�ɾ��
    Boolean deleteSubject(Integer id);

    //�û����
    Boolean addSubject(Subject subject);

}
