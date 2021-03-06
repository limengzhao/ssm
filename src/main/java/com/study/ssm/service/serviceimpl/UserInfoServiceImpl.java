package com.study.ssm.service.serviceimpl;

import java.util.List;

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
    public User findUserById(Integer userid) {
        
        return userInfoDao.findUserById (userid);
    }


    @Override
    public User userLogin(String username, String password) {
        
        return userInfoDao.userLogin (username , password);
    }


    @Override
    public void inserUser(User user) {
        userInfoDao.inserUser (user);
    }


    @Override
    public User findUserByUserName(String userName) {
        
        return userInfoDao.findUserByUserName (userName);
    }


	@Override
	public List<User> findUserAll(Integer userid,Integer limit,Integer pageNumber) {
		
		return userInfoDao.findAllUser(userid, limit, pageNumber);
	}


    @Override
    public void deleteUserById(Integer userid) {
        userInfoDao.deleteUserById (userid);
    }


	@Override
	public void updateUser(User user) {
		userInfoDao.updateUser(user);
	}


    @Override
    public int selectPageCount(Integer userid, Integer limit, Integer pageNumber) {
        // TODO Auto-generated method stub
        return userInfoDao.selectPageCount (userid , limit , pageNumber);
    }
    
}
