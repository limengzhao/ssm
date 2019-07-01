package com.study.ssm.service;

import java.util.List;

import com.study.ssm.core.Menu;

public interface MenuInfoService {
    
    List<Menu> findPage(Integer menuid,Integer limit,Integer pageNumber);
    
    Integer selectPageCount(Integer menuid,Integer limit,Integer pageNumber);
    
    void saveMenu(Menu menu);
    
    Menu findMenuById(Integer menuid);
    
    void updateMenu(Menu menu);
    
    List<Menu> getParentMenu();
    
    List<Menu>findAll();
    
    void deleteMenu(Integer menuid);
}
