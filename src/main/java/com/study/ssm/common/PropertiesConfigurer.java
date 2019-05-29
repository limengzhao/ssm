package com.study.ssm.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;

public class PropertiesConfigurer{
    
    private static Map<String,Object> propertiesMap;
    
    public PropertiesConfigurer() {
        
    }
    
    
    protected static void processProperties(Properties props)throws BeansException {
        propertiesMap=new HashMap<String, Object>();
        for (Object proKey : props.keySet ()) {
            String key=proKey.toString ();
            String value=props.getProperty (key);
            propertiesMap.put (key , value);
        }
        
    }
    
    
    
    public static Object getProperty(String key){
        return propertiesMap.get (key);
    }
    
    public static Object setProperty(String name,String value){
        return propertiesMap.put (name , value);
    }
    
}
