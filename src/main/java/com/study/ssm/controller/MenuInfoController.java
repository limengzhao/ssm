package com.study.ssm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentMap;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.ssm.core.Menu;
import com.study.ssm.service.serviceimpl.MenuInfoServiceImpl;
import com.study.ssm.utils.ModelView;
import com.study.ssm.utils.ResultMap;



@Controller
public class MenuInfoController {
    
    Logger logger=LoggerFactory.getLogger (MenuInfoController.class);
    
    @Autowired
    private MenuInfoServiceImpl menuInfoServiceImpl;
    
    @RequestMapping(value="/getParentMenu",method=RequestMethod.GET)
    @ResponseBody
    public List<Menu> getParentMenu(Model model){
        List<Menu> menuList=menuInfoServiceImpl.getParentMenu ();
        if(!menuList.isEmpty ()){
            ModelView.modelView ("menuList" , menuList , model);
            return menuList;
        }else{
            return null;
        }
        
    }
    
    
    
    
    
    /**
     * 跳转菜单页面
     * @return
     */
    @RequestMapping(value="/findMenuPanel")
    public String findMenuPage(){
        
        return "view/menu/menu";
    }
    /**
     * 跳转菜单添加页面
     * @return
     */
    @RequestMapping(value="/addMenuPage")
    public String addMenuPage(Model model){
        ModelView.modelView ("title" , "添加菜单" , model);
        return "view/menu/addMenu";
    }
    /**
     * 跳转菜单更新页面
     * @return
     */
    @RequestMapping(value="/updateMenuPage")
    public String updateMenuPage(Integer menuid,Model model){
        Menu menu=menuInfoServiceImpl.findMenuById (menuid);
        ModelView.modelView ("title" , "更新菜单" , model);
        ModelView.modelView ("menu" , menu , model);
        return "view/menu/addMenu";
    }
    /**
     * 更新menu
     * @param menu
     * @return
     */
    @RequestMapping(value="/updateMenu",method=RequestMethod.POST)
    @ResponseBody
    public String updateMenu(Menu menu){
        menu.setUpdateTime (new Date ());
        menu.setUpdateUser (1);
        menuInfoServiceImpl.updateMenu (menu);
        return "true";
    }
    
    @RequestMapping(value="/findPageMenu")
    @ResponseBody
    public ConcurrentMap<String, Object> findMenuAll(int limit,int page,Integer menuid){
        Integer pageNumber=(page - 1) * limit;
        List<Menu> addMenuList=new ArrayList<Menu> ();
        if(menuInfoServiceImpl!=null){
            List<Menu> menuList=menuInfoServiceImpl.findPage (menuid , limit , pageNumber);
            List<Menu> menuListParent=menuInfoServiceImpl.findAll ();
            for (Menu menu : menuList) {
                Integer pid=menu.getParentMenuId ();
                if(pid!=0){
                    for (Menu menu2 : menuListParent) {
                        if(menu2.getMenuid ()==pid){
                            menu.setParentMenu (menu2.getMenuName ()); 
                        }
                    } 
                }else{
                    menu.setParentMenu (""); 
                }
                addMenuList.add (menu);
            }
            int pageCount=menuInfoServiceImpl.selectPageCount (menuid,limit,pageNumber);
            ResultMap resultMap=new ResultMap ();
            resultMap.setCode (0);
            resultMap.setMsg ("查询成功");
            resultMap.setObjList (addMenuList);
            resultMap.setCount (pageCount);
            return resultMap.getConcurrentMap ();
        }else {
            //menuInfoServiceImpl=new MenuInfoServiceImpl ();
            System.out.println ("menuInfoServiceImpl为空！！！");
            return null;
        }
    }
    /**
     * saveMenu
     * @param menu
     * @return
     */
    @RequestMapping(value="/saveMenu",method=RequestMethod.POST)
    @ResponseBody
    public String saveMenu(Menu menu){
        menu.setCreateTime (new Date ());
        menu.setCrateMenuUser (0);
        menuInfoServiceImpl.saveMenu (menu);
        return "true";
    }
    /**
     * 删除菜单
     * @param menuid
     * @return
     */
    @RequestMapping(value="/deleteMenu")
    @ResponseBody
    public String deleteMenu(Integer menuid){
        try {
            menuInfoServiceImpl.deleteMenu (menuid);
        } catch (Exception e) {
          return "false";
        }
        return "true";
    }
    
}
