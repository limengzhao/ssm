package com.study.ssm.service;

import java.util.List;

import com.study.ssm.core.Menu;

public interface MenuInfoService {
    
    List<Menu> findMenuAll(int menuid,int limit,Integer pageNumber);
    
    int selectPageCount(int menuid,int limit,Integer pageNumber);
    
}
