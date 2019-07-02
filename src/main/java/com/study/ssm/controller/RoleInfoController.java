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
     * ��ת��ɫҳ��
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
            resultMap.setMsg ("��ѯ�ɹ�");
            resultMap.setObjList (roleList);
            resultMap.setCount (pageCount);
            return resultMap.getConcurrentMap ();
        }else{
            System.out.println ("roleMenuInfoServiceImplΪ�գ�����");
            return null;
        }
        
    }
    /**
     * ��ת����ҳ��
     * @return
     */
    @RequestMapping(value="/addRolePage")
    public String addRolePage(Model model){
        ModelView.modelView ("title" , "��ӽ�ɫ" , model);
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
        ModelView.modelView ("title" , "���½�ɫ" , model);
        ModelView.modelView ("role" , role , model);
        return "view/role/addOrUpdateRole";
    }
    /**
     * ���½�ɫ
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
