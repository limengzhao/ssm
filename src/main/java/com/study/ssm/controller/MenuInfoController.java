package com.study.ssm.controller;

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
    
    
    /**
     * ��ת�˵�ҳ��
     * @return
     */
    @RequestMapping(value="/findMenuPage")
    public String findMenuPage(){
        
        return "view/menu/menu";
    }
    /**
     * ��ת�˵����ҳ��
     * @return
     */
    @RequestMapping(value="/addMenuPage")
    public String addMenuPage(Model model){
        ModelView.modelView ("title" , "��Ӳ˵�" , model);
        return "view/menu/addMenu";
    }
    /**
     * ��ת�˵�����ҳ��
     * @return
     */
    @RequestMapping(value="/updateMenuPage")
    public String updateMenuPage(Integer menuid,Model model){
        Menu menu=menuInfoServiceImpl.findMenuById (menuid);
        ModelView.modelView ("title" , "���²˵�" , model);
        ModelView.modelView ("menu" , menu , model);
        return "view/menu/addMenu";
    }
    @RequestMapping(value="/updateMenu",method=RequestMethod.POST)
    public String updateMenu(Menu menu){
        menuInfoServiceImpl.updateMenu (menu);
        return "true";
    }
    
    @RequestMapping(value="/findMenuAll")
    @ResponseBody
    public ConcurrentMap<String, Object> findMenuAll(int limit,int page,Integer menuid){
        Integer pageNumber=(page - 1) * limit;
        if(menuInfoServiceImpl!=null){
            List<Menu> menuList=menuInfoServiceImpl.findAll (menuid , limit , pageNumber);
            int pageCount=menuInfoServiceImpl.selectPageCount (menuid,limit,pageNumber);
            ResultMap resultMap=new ResultMap ();
            resultMap.setCode (0);
            resultMap.setMsg ("��ѯ�ɹ�");
            resultMap.setObjList (menuList);
            resultMap.setCount (pageCount);
            return resultMap.getConcurrentMap ();
        }else {
            //menuInfoServiceImpl=new MenuInfoServiceImpl ();
            System.out.println ("menuInfoServiceImplΪ�գ�����");
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
    
}
