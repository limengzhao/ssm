package com.study.ssm.service;



import java.util.List;


import com.study.ssm.core.User;


public interface UserInfoService{
	
	
	List<User> findUserAll(Integer userid,Integer limit,Integer pageNumber);
	int selectPageCount(Integer userid,Integer limit,Integer pageNumber);
    
    User findUserById(Integer id);
    
    User userLogin(String username,String password);
    
    void inserUser(User user);
    
    User findUserByUserName(String userName);
    
    void deleteUserById(Integer id);
    
    void updateUser(User user);
}
