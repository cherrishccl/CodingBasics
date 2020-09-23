package com.boot.basics.coding.mq.rabbit.service;

import com.boot.basics.coding.mq.rabbit.dao.TmpUser;
import com.boot.basics.coding.mq.rabbit.dao.TmpUserMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author cherrishccl
 * @Date 2020/9/23 16:48
 * @Version 1.0
 * @Description
 */
public class BatchService {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public void batch1(){
        SqlSession session = sqlSessionTemplate.getSqlSessionFactory()
                .openSession(ExecutorType.BATCH,false);
        TmpUserMapper tmpUserMapper = session.getMapper(TmpUserMapper.class);
        for(int i = 0; i < 1000000;){
            tmpUserMapper.insert(new TmpUser());
            i++;
            if(i % 1000 == 0){
                session.commit();
                session.clearCache();
            }
        }
        session.commit();
    }
}
