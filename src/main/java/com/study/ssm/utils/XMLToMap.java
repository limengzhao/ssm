package com.study.ssm.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/**
 * 
 * @author limengzhao 20190412
 *
 */
public class XMLToMap {
    /***
     * <?xml version="1.0" encoding="UTF-8"?>
        <books name="admin">
           <book1 id="001" categlory="数学">
              <title>Harry Potter</title>
              <author>J K. Rowling</author>
           </book1>
           <book2 id="002" categlory="历史">
              <title>Learning XML</title>
              <author>Erik T. Ray</author>
           </book2>
        </books>
     */
    
    /**
     * dom4j解析XML文件
     * 获取XMl根元素 src/main/resources/book.xml
     * @param path
     */
    public static Element  getXMLRootElement(String path){
        SAXReader reader = new SAXReader();
        File file=new File (path);
        try {
            Document document=reader.read (file);
            Element rootElement = document.getRootElement();
            return rootElement;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
        
    }
    
    
    
    /**
     * 使用递归方法完成xml转Map
     * @param element
     * @param outXMLMap
     * @return
     */
    public static Map<String,Map<String,Map<String,String>>> loadXMLMap(Element element,Map outXMLMap){
        @SuppressWarnings("unchecked")
        Map map=new HashMap ();
        if(element.elements ().size ()==0){
            outXMLMap.put (element.getName () , element.getTextTrim ()); 
        }else{
            Iterator<Element> elIterator= element.elementIterator ();
            while(elIterator.hasNext ()){
                Element el=elIterator.next ();
                outXMLMap.put (element.getName () , loadXMLMap(el,map));
            }
        }
        return outXMLMap;
    }
    
    
    
    public static void main(String[] args) {
        Element element=XMLToMap.getXMLRootElement ("src/main/resources/mybatis-config.xml");
        Map outXMLMap=new HashMap(); 
        Map<String, Map<String, Map<String, String>>> map=loadXMLMap(element,outXMLMap);
        System.out.println (map.toString ());
    }
    
}
