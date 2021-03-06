package com.study.ssm.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.study.ssm.common.PropertiesConfigurer;
import org.springframework.stereotype.Controller;
import com.study.ssm.core.User;
import org.springframework.ui.Model;
import com.study.ssm.service.serviceimpl.UserInfoServiceImpl;
import com.study.ssm.utils.Constant;
import com.study.ssm.utils.ModelView;
import com.study.ssm.utils.MsgConstant;
import com.study.ssm.utils.ResultMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



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
          //将用户名保存到cookie中,因为cookie不支持中文使用URL进行编码
            Cookie cookie = null;
            try {
                cookie = new Cookie("cookieLoginName", URLEncoder.encode(user.getUsername (), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            cookie.setMaxAge(60*60*24);//cookie的有效期是一天
            //添加cookie对象,把cookier对象返回给浏览器
            response.addCookie(cookie);
            logger.debug ("登录成功");
            ModelView.modelView ("user" , user , model);
            return "view/index";
        }else{
            logger.debug ("登录失败");
            ModelView.modelView (Constant.ERROR , MsgConstant.USER_LOGIN_FAIL_MSG , model);
            return Constant.VIEW_ERROR;
        }
    }
    /**
     * 退出
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/loginOut",method=RequestMethod.GET)
    public String loginOut(HttpServletRequest request,HttpServletResponse response){
        request.getSession().invalidate();//让session对象无效
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
        System.out.println ("退出");
        return "../loginOut";
    }
    
    /**
     * 进入注册
     * @return
     */
    @RequestMapping(value="/register")
    public String register(String type,Model model){
        System.out.println ("进入注册页面"+type);
        ModelView.modelView ("type" , type , model);
        return "../register";
    }
    /**
     * 跳转到更新页面
     * @param model
     * @return
     */
    @RequestMapping(value="/userUpdate")
    public String update(Integer userid,Model model){
        System.out.println ("userid==="+userid);
        User user=userInfoServiceImpl.findUserById(userid);
        ModelView.modelView ("user" , user , model);
        return "view/user/update";
    }
    /**
     * 新增用户
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
        System.out.println ("注册成功");
        if("login".equals (type)){
            return "login";
        }else if("add".equals (type)){
            return "add";
        }
        return "注册失败";
    }
    /**
     * 校验用户名是否可用
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
     * 查询全部用户数据
     * @param userid //用于重载数据查询
     * @return
     */
    @RequestMapping(value = "/findAll")
    @ResponseBody
    public ConcurrentMap<String, Object> findAllUser(int limit/****/,int page,Integer userid) {
        System.out.println ( "limit==="+limit+"pageNumber======"+page);
         Integer pageNumber=(page - 1) * limit;
        List<User> userList=userInfoServiceImpl.findUserAll(userid,limit,pageNumber);
        Integer pageCount=userInfoServiceImpl.selectPageCount (userid , limit , pageNumber);
        ResultMap resultMap=new ResultMap ();
        resultMap.setCode (0);
        resultMap.setMsg ("成功");
        resultMap.setCount (pageCount);
        resultMap.setObjList (userList);
    	return resultMap.getConcurrentMap ();
    }
    /**
            * 删除用户
     * @param userid
     * @return
     */
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
    /**
     * 根据userId更新数据
     * @param user
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public String updateUserById(User user) {
    	System.out.println("updateUser===="+user.getRealname());
    	user.setUpdateTime (new Date ());
    	user.setUpdateUser (user.getUserid ());//登录用户ID
    	userInfoServiceImpl.updateUser(user);
    	
    	return "0";
    }
    
}
