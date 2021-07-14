package com.boot.basics.coding.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.mapper.Mapper;

import java.util.List;

/**
 * @Author cherrishccl
 * @Date 2020/8/24 10:55
 * @Version 1.0
 * @Description
 */
public interface UserMapper extends BaseMapper<User> {
    // @Select("select * from t_user where id = #{id}")
    User selectUser(Integer id);
    List<User> selectUserByName(User user);
}
