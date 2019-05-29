package com.study.ssm.service;



import java.util.List;

import com.study.ssm.core.User;


public interface UserInfoService{
	
	
	List<User> findUserAll();
    
    User findUserById(Integer id);
    
    User userLogin(String username,String password);
    
    void inserUser(User user);
    
    User findUserByUserName(String userName);
}
