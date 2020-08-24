package com.boot.basics.coding.mybatis.dao;

/**
 * @Author cherrishccl
 * @Date 2020/8/24 10:55
 * @Version 1.0
 * @Description
 */
public interface UserMapper {
    // @Select("select * from t_user where id = #{id}")
    User selectUser(Integer id);
}
