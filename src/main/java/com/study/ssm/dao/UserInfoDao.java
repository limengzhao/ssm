package com.study.ssm.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.study.ssm.core.User;


public interface UserInfoDao {
	
	List<User> findAllUser(@Param(value="userid") Integer userid);
    
    User findUserById(Integer id);
    
    User userLogin(@Param("username")String username,@Param("password")String password);
    
    void inserUser(User user);
    
    User findUserByUserName(@Param("username")String userName);
    
    void deleteUserById(Integer userid);

}
