package com.study.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.study.ssm.core.Menu;


public interface MenuInfoDao {
    
     List<Menu> findAll(@Param(value="menuid")Integer menuid,@Param(value="limit")Integer limit,@Param(value="pageNumber")Integer pageNumber);
    
     Integer selectPageCount(@Param(value="menuid")Integer menuid,@Param(value="limit")Integer limit,@Param(value="pageNumber")Integer pageNumber);
     
     void saveMenu(Menu menu);
     
     Menu findMenuById(@Param(value="menuid")Integer menuid);
    
     void updateMenu(Menu menu);
    
}
