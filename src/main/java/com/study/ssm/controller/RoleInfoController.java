package com.study.ssm.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.ssm.core.Role;
import com.study.ssm.service.serviceimpl.RoleMenuInfoServiceImpl;
import com.study.ssm.utils.ModelView;
import com.study.ssm.utils.ResultMap;

@Controller
public class RoleInfoController {
    
    @Autowired
    RoleMenuInfoServiceImpl roleMenuInfoSreviceImpl;
    
    /**
     * 跳转角色页面
     * @return
     */
    @RequestMapping(value="/findRolePage")
    public String findRolePage(){
        
        return "view/role/role";
    }
    
    @RequestMapping(value="/findRoleData")
    @ResponseBody
    public ConcurrentMap<String, Object> findRoleData(Integer roleId,int limit,int page){
        Integer pageNumber=(page - 1) * limit;
        //List<Role> addRoleList=new ArrayList<Role> ();
        List<Role> roleList=roleMenuInfoSreviceImpl.findRoleAll (roleId,limit,pageNumber);
        if(roleList.size ()>0){
            Integer pageCount=roleMenuInfoSreviceImpl.selectPageCount (roleId , limit , pageNumber);
            ResultMap resultMap=new ResultMap ();
            resultMap.setCode (0);
            resultMap.setMsg ("查询成功");
            resultMap.setObjList (roleList);
            resultMap.setCount (pageCount);
            return resultMap.getConcurrentMap ();
        }else{
            System.out.println ("roleMenuInfoServiceImpl为空！！！");
            return null;
        }
        
    }
    /**
     * 跳转新增页面
     * @return
     */
    @RequestMapping(value="/addRolePage")
    public String addRolePage(Model model){
        ModelView.modelView ("title" , "添加角色" , model);
        return "view/role/addOrUpdateRole";
    }
    /**
     * saveRole
     * @param role
     * @return
     */
    
    @RequestMapping(value="/saveRole")
    @ResponseBody
    public String saveRole(Role role){
        role.setRegDate (new Date());
        roleMenuInfoSreviceImpl.saveRole (role);
        return "true";
    }
    @RequestMapping(value="/findUpdateRolePage")
    public String findUpdateRolePage(Integer roleId,Model model){
        Role role=roleMenuInfoSreviceImpl.findRoleById (roleId);
        ModelView.modelView ("title" , "更新角色" , model);
        ModelView.modelView ("role" , role , model);
        return "view/role/addOrUpdateRole";
    }
    /**
     * 更新角色
     * @param role
     * @return
     */
    
    @RequestMapping(value="/updateRole")
    @ResponseBody
    public String updateRole(Role role){
        roleMenuInfoSreviceImpl.updateRole (role);
        return "true";
    }
    @RequestMapping(value="/deleteRole",method=RequestMethod.GET)
    @ResponseBody
    public String deleteRole(Integer roleId){
        try {
            roleMenuInfoSreviceImpl.deleteRole (roleId);
        } catch (Exception e) {
           return "false";
        }
        return "true";
    }
}
