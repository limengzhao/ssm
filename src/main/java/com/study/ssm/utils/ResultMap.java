package com.study.ssm.utils;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.study.ssm.core.Menu;

public class ResultMap {
    
    private Integer code;
    private String msg;
    private List<?> ObjList;
    private Integer count;
    
    private ConcurrentMap<String, Object> concurrentMap=new ConcurrentHashMap<> ();
    
    public ResultMap(){
        
    }
    
    public ResultMap(Integer code,String msg,List<Menu> ObjList,Integer count){
        this.code=code;
        this.msg=msg;
        this.ObjList=ObjList;
        this.count=count;
    }
    
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public List<?> getObjList() {
        return ObjList;
    }

    public void setObjList(List<?> objList) {
        ObjList = objList;
    }

    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }

    public ConcurrentMap<String, Object> getConcurrentMap() {
            concurrentMapPutValue();
        return concurrentMap;
    }

    public void setConcurrentMap(ConcurrentMap<String, Object> concurrentMap) {
        this.concurrentMap = concurrentMap;
    }
    
    public  void concurrentMapPutValue(){
        concurrentMap.put ("code" , code);
        concurrentMap.put ("msg" , msg);
        concurrentMap.put ("data" , ObjList);
        concurrentMap.put ("count" , count);
    }
    
    
}
