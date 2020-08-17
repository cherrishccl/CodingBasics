package com.boot.basics.coding.mq.rabbit.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserMapper {
    int insert(TUser tUser);
}