package com.study.ssm.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
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
    /**
     * 遍历XML所有的标签的属性
     * @param node
     */
    public  void getNods(Element node){
        System.out.println("--------------------");
      //当前节点的名称、文本内容和属性
        System.out.println("当前节点名称："+node.getName());//当前节点名称
        System.out.println("当前节点的内容："+node.getTextTrim());//当前节点名称
        List<Attribute> list=node.attributes ();
        for (Attribute attr : list) {
            String name=attr.getName();//属性名称
            String value=attr.getValue();//属性的值
            System.out.println("属性名称："+name+"属性值："+value);
        }
        List<Element> eList=node.elements ();//遍历所有子节点
        for (Element element : eList) {
            this.getNods(element);
        }
    }
    
    
    public static void main(String[] args) {
        Element element=XMLToMap.getXMLRootElement ("src/main/resources/mybatis-config.xml");
//        Map outXMLMap=new HashMap(); 
//        Map<String, Map<String, Map<String, String>>> map=loadXMLMap(element,outXMLMap);
//        System.out.println (map.toString ());
        XMLToMap xm=new XMLToMap ();
        xm.getNods (element);
    }
    
}
