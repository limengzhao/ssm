package com.study.ssm.service;

import java.util.List;

import com.study.ssm.core.Menu;

public interface MenuInfoService {
    
    List<Menu> findAll(Integer menuid,Integer limit,Integer pageNumber);
    
    Integer selectPageCount(Integer menuid,Integer limit,Integer pageNumber);
    
    void saveMenu(Menu menu);
    
    Menu findMenuById(Integer menuid);
}
