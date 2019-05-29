package com.study.ssm.service;


import com.study.ssm.core.User;


public interface UserInfoService{
    
    User findUserById(Integer id);
    
    User userLogin(String username,String password);
}
