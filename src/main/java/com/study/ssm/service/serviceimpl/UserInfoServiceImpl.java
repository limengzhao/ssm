package com.study.ssm.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.ssm.core.User;
import com.study.ssm.dao.UserInfoDao;
import com.study.ssm.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{

    @Autowired
    UserInfoDao userInfoDao;
    
    
    @Override
    public User findUserById(Integer id) {
        
        return userInfoDao.findUserById (id);
    }


    @Override
    public User userLogin(String username, String password) {
        
        return userInfoDao.userLogin (username , password);
    }
    
}
