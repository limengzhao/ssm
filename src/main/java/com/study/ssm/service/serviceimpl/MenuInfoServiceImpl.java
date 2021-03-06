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
    public List<Menu> findPage(Integer menuid, Integer limit, Integer pageNumber) {
        
        return menuInfoDao.findPage (menuid , limit , pageNumber);
    }
    
    @Override
    public Integer selectPageCount(Integer menuid, Integer limit, Integer pageNumber) {
        
        return menuInfoDao.selectPageCount (menuid , limit , pageNumber);
    }

    @Override
    public void saveMenu(Menu menu) {
        menuInfoDao.saveMenu (menu);
    }

    @Override
    public Menu findMenuById(Integer menuid) {
       
        return menuInfoDao.findMenuById (menuid);
    }

    @Override
    public void updateMenu(Menu menu) {
        menuInfoDao.updateMenu (menu);
    }

    @Override
    public List<Menu> getParentMenu() {
        
        return menuInfoDao.getParentMenu ();
    }

    @Override
    public List<Menu> findAll() {
        return menuInfoDao.findAll ();
    }

    @Override
    public void deleteMenu(Integer menuid) {
        menuInfoDao.deleteMenu (menuid);
    }
    
}
