package com.study.ssm.dao;


import org.apache.ibatis.annotations.Param;

import com.study.ssm.core.User;


public interface UserInfoDao {
    
    User findUserById(Integer id);
    
    User userLogin(@Param("username")String username,@Param("password")String password);
    
    void inserUser(User user);
    
    User findUserByUserName(@Param("username")String userName);

}
