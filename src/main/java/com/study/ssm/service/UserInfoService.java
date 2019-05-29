package com.study.ssm.service;


import org.apache.ibatis.annotations.Param;

import com.study.ssm.core.User;


public interface UserInfoService{
    
    User findUserById(Integer id);
    
    User userLogin(String username,String password);
    
    void inserUser(User user);
    
    User findUserByUserName(String userName);
}
