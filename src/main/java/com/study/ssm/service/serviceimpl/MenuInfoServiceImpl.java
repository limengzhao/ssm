package com.study.ssm.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.ssm.core.Menu;
import com.study.ssm.dao.MenuInfoDao;
import com.study.ssm.service.MenuInfoService;

@Service
public class MenuInfoServiceImpl implements MenuInfoService {
    
    @Autowired
    MenuInfoDao menuInfoDao;

    @Override 
    public List<Menu> findMenuAll(int menuid,int limit,Integer pageNumber) {
        // TODO Auto-generated method stub
        return menuInfoDao.findMenuAll (menuid,limit,pageNumber);
    }

    @Override
    public int selectPageCount(int menuid,int limit,Integer pageNumber) {
        // TODO Auto-generated method stub
        return menuInfoDao.selectPageCount (menuid,limit,pageNumber);
    }
    
}
