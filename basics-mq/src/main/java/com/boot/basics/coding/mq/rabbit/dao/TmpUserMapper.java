package com.boot.basics.coding.mq.rabbit.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TmpUserMapper {
    long countByExample(TmpUserExample example);

    int deleteByExample(TmpUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TmpUser record);

    int insertSelective(TmpUser record);

    List<TmpUser> selectByExample(TmpUserExample example);

    TmpUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TmpUser record, @Param("example") TmpUserExample example);

    int updateByExample(@Param("record") TmpUser record, @Param("example") TmpUserExample example);

    int updateByPrimaryKeySelective(TmpUser record);

    int updateByPrimaryKey(TmpUser record);

    void insertBatch(List<TmpUser> records);
}