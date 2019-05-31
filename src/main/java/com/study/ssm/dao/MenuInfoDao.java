package com.study.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.study.ssm.core.Menu;


public interface MenuInfoDao {
    
    List<Menu> findMenuAll(@Param(value="menuid")Integer menuid,@Param(value="limit")Integer limit,@Param(value="pageNumber")Integer pageNumber);
    
    int selectPageCount(@Param(value="menuid")Integer menuid,@Param(value="limit")Integer limit,@Param(value="pageNumber")Integer pageNumber);
    
    
}
