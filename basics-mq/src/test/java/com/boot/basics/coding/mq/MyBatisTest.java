package com.boot.basics.coding.mq;

import com.boot.basics.coding.mq.rabbit.dao.TUser;
import com.boot.basics.coding.mq.rabbit.dao.TUserMapper;
import com.boot.basics.coding.mq.rabbit.dao.TmpUser;
import com.boot.basics.coding.mq.rabbit.dao.TmpUserMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author cherrishccl
 * @Date 2020/8/17 10:35
 * @Version 1.0
 * @Description
 */
@SpringBootTest
public class MyBatisTest {
    @Autowired
    private TmpUserMapper tmpUserMapper;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    @Autowired
    private TUserMapper tUserMapper;

    @Test
    void contextLoads() {
        TUser tUser = new TUser();
        tUser.setAge((short) 12);
        tUser.setSalary(BigDecimal.valueOf(10));
        tUser.setUserName("test");
        tUser.setMaxSize(1111);
        tUserMapper.insert(tUser);
    }

    @Test
    void testSelect(){
        System.out.println(tmpUserMapper.selectByPrimaryKey(12));
    }

    @Test
    void testBatch(){
        long start =System.currentTimeMillis();
        // batch1();
        // batch2();
        // batch3();
        System.out.println("Cost Time = " + (System.currentTimeMillis() - start));
    }
    void batch1(){//82876ms
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(false);
        TmpUserMapper tmpUserMapper = sqlSession.getMapper(TmpUserMapper.class);
        TmpUser tmpUser = new TmpUser();
        for(int i = 1000001; i <=1000500; i++){
            tmpUser.setUserName("user" + i);
            tmpUser.setAge((short) (i % 30));
            tmpUser.setCertNo("1000" + i);
            tmpUser.setAddress("重庆市" + i);
            tmpUser.setRemark("江北区" + i);
            tmpUser.setSalary(BigDecimal.valueOf(5000));
            tmpUserMapper.insert(tmpUser);
        }
        sqlSession.commit();
    }
    void batch2(){//27196ms
        //跟上述sql区别
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        TmpUserMapper tmpUserMapper = sqlSession.getMapper(TmpUserMapper.class);
        TmpUser tmpUser = new TmpUser();
        for(int i = 1000501; i <= 1001000; i++){
            tmpUser.setUserName("user" + i);
            tmpUser.setAge((short) (i % 30));
            tmpUser.setCertNo("1000" + i);
            tmpUser.setAddress("重庆市" + i);
            tmpUser.setRemark("江北区" + i);
            tmpUser.setSalary(BigDecimal.valueOf(5000));
            tmpUserMapper.insert(tmpUser);
        }
        sqlSession.commit();
    }
    void batch3(){//1072ms
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        TmpUserMapper tmpUserMapper = sqlSession.getMapper(TmpUserMapper.class);
        TmpUser tmpUser = null;
        List<TmpUser> users = new ArrayList<>(500);
        for(int i = 1001001; i <= 1001500; i++){
            tmpUser = new TmpUser();
            tmpUser.setUserName("user" + i);
            tmpUser.setAge((short) (i % 30));
            tmpUser.setCertNo("1000" + i);
            tmpUser.setAddress("重庆市" + i);
            tmpUser.setRemark("江北区" + i);
            tmpUser.setSalary(BigDecimal.valueOf(5000));
            users.add(tmpUser);
        }
        tmpUserMapper.insertBatch(users);
        sqlSession.commit();
    }
}
