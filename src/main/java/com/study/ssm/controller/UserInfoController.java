package com.study.ssm.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.study.ssm.common.PropertiesConfigurer;
import com.study.ssm.core.User;
import com.study.ssm.service.serviceimpl.UserInfoServiceImpl;
import com.study.ssm.utils.Constant;
import com.study.ssm.utils.ModelView;
import com.study.ssm.utils.MsgConstant;

@Controller
public class UserInfoController {
    Logger logger=LoggerFactory.getLogger (UserInfoController.class);
    
    @Autowired
    UserInfoServiceImpl userInfoServiceImpl;
    
    
    @RequestMapping(value="/getUserById",method=RequestMethod.GET)
    public User getUserById(Integer id){
        
        return userInfoServiceImpl.findUserById(1);
    }
    
    @RequestMapping(value = "/findUserPage")
    public String findUserPage() {
    	return "view/user/user";
    }
    
    
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public String login(HttpServletRequest request,
            HttpServletResponse response,Model model){
        String username=request.getParameter ("username");
        String password=request.getParameter ("password");
        System.out.println (username+"==="+password);
        User user=userInfoServiceImpl.userLogin (username , password);
        if(user!=null){
            if(!Constant.STATUS_SCUESS.equals (user.getStatus ())){
                ModelView.modelView (Constant.ERROR , PropertiesConfigurer.getProperty (MsgConstant.USER_STATU_INVALID_MSG) , model);
                return Constant.VIEW_ERROR;
            }
            request.getSession ().setAttribute ("seesionuser" , user);
          //���û������浽cookie��,��Ϊcookie��֧������ʹ��URL���б���
            Cookie cookie = null;
            try {
                cookie = new Cookie("cookieLoginName", URLEncoder.encode(user.getUsername (), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            cookie.setMaxAge(60*60*24);//cookie����Ч����һ��
            //���cookie����,��cookier���󷵻ظ������
            response.addCookie(cookie);
            logger.debug ("��¼�ɹ�");
            ModelView.modelView ("user" , user , model);
            return "view/index";
        }else{
            logger.debug ("��¼ʧ��");
            ModelView.modelView (Constant.ERROR , MsgConstant.USER_LOGIN_FAIL_MSG , model);
            return Constant.VIEW_ERROR;
        }
    }
    /**
     * �˳�
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/loginOut",method=RequestMethod.GET)
    public String loginOut(HttpServletRequest request,HttpServletResponse response){
        request.getSession().invalidate();//��session������Ч
        Cookie[] cookie=request.getCookies ();
        if(cookie!=null&&cookie.length>0){
            for (Cookie c : cookie) {
                if("cookieLoginName".equals (c.getName ())){
                    c.setMaxAge (0);
                    System.out.println ("aaaaaaaa");
                    response.addCookie(c);
                    break;
                }
            }
        }
        System.out.println ("�˳�");
        return "../loginOut";
    }
    
    /**
     * ����ע��
     * @return
     */
    @RequestMapping(value="/register")
    public String register(String type,Model model){
        System.out.println ("����ע��ҳ��"+type);
        ModelView.modelView ("type" , type , model);
        return "../register";
    }
    /**
     * ��ת������ҳ��
     * @param model
     * @return
     */
    @RequestMapping(value="/userUpdate")
    public String update(String userid,Model model){
        ModelView.modelView ("id" , "1" , model);
        return "view/user/update";
    }
    /**
     * �����û�
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    @ResponseBody
    public String saveUser(HttpServletRequest request,HttpServletResponse response){
        User user=new User ();
        String username=request.getParameter ("username");
        user.setUsername (username);
        String status=request.getParameter ("status");
        user.setStatus (status);
        String password=request.getParameter ("password");
        user.setPassword (password);
        String phone=request.getParameter ("phone");
        user.setPhone (phone);
        String realname=request.getParameter ("realname");
        user.setRealname (realname);
        String email=request.getParameter ("email");
        user.setEmail (email);
        user.setCreateUser (1);
        user.setCreateTime (new Date ());
        userInfoServiceImpl.inserUser (user);
        String type=request.getParameter ("type");
        System.out.println ("username====="+username+" status===="+status +" password==="+password+" type==="+type);
        System.out.println ("ע��ɹ�");
        if("login".equals (type)){
            return "login";
        }else if("add".equals (type)){
            return "add";
        }
        return "ע��ʧ��";
    }
    /**
     * У���û����Ƿ����
     * @param username
     * @return
     */
    @RequestMapping(value="/username.action")
    @ResponseBody
    public String findUserByUserName(String username){
        System.out.println ("userName==="+username);
        User count=userInfoServiceImpl.findUserByUserName (username);
        System.out.println (count);
        if(count!=null){
            return "1";
        }
        return "0";
    }
    /**
     * ��ѯȫ���û�����
     * @param model
     * @return
     */
    @RequestMapping(value = "/findAll")
    @ResponseBody
    public ConcurrentMap<String, Object> findAllUser(Integer userid) {
        List<User> userList=userInfoServiceImpl.findUserAll(userid);
        JSONArray jsonArray=JSONArray.parseArray(JSONObject.toJSONString(userList));
        System.out.println(jsonArray.toJSONString());
        ConcurrentMap<String, Object> concurrentMap = new ConcurrentHashMap<String,Object>();
        concurrentMap.put ("code" , 0);
        concurrentMap.put ("msg" , "�ɹ�");
        concurrentMap.put ("count" , userList.size ());
        concurrentMap.put ("data" , userList);
    	return concurrentMap;
    }
    @RequestMapping(value="/deleteUser",method=RequestMethod.GET)
    @ResponseBody
    public String deleteUserById(Integer userid){
        try{
            System.out.println ("userid===="+userid);
            userInfoServiceImpl.deleteUserById (userid);
        }catch (Exception e) {
           return "1";
        }
        return "0";
    }
    
}
