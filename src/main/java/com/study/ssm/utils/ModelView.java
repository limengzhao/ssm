package com.study.ssm.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

public class ModelView {
    
    
   //String
    public static void modelView(String modelKey, String message, Model model){
        model.addAttribute (modelKey , message);
    }
    
    
    //List
    public static void modelView(String modelKey,List<?> list, Model model){
        model.addAttribute (modelKey , list);
    }
    
    //Map
    public static void modelView(String modelKey,Map<?,?> map, Model model){
        model.addAttribute (modelKey , map);
    } 
    //สýื้
    public static void modelView(String modelKey,Arrays arrays, Model model){
        model.addAttribute (modelKey , arrays);
    }
    
    //Object
    public static void modelView(String modelKey,Object obj, Model model){
        model.addAttribute (modelKey , obj);
    }
    
    
    
}
