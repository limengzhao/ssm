package com.study.ssm.controller;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.ssm.core.Menu;
import com.study.ssm.service.serviceimpl.MenuInfoServiceImpl;
import com.study.ssm.utils.ResultMap;

@Controller
public class MenuInfoController {
    Logger logger=LoggerFactory.getLogger (MenuInfoController.class);
    
    @Autowired
    MenuInfoServiceImpl menuInfoServiceImpl;
    
    @RequestMapping(value="/findMenuPage")
    public String findMenuPage(){
        
        return "view/menu/menu";
    }
    
    @RequestMapping(value="/findMenuAll")
    @ResponseBody
    public ConcurrentMap<String, Object> findMenuAll(int limit,int page,Integer menuid){
        Integer pageNumber=(page - 1) * limit;
        List<Menu> menuList=menuInfoServiceImpl.findMenuAll (menuid,limit,pageNumber);
        int pageCount=menuInfoServiceImpl.selectPageCount (menuid,limit,pageNumber);
        ResultMap resultMap=new ResultMap ();
        resultMap.setCode (0);
        resultMap.setMsg ("³É¹¦");
        resultMap.setObjList (menuList);
        resultMap.setCount (pageCount);
        return resultMap.getConcurrentMap ();
    }
    
}
