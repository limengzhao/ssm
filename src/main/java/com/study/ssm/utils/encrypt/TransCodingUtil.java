package com.study.ssm.utils.encrypt;

import java.io.UnsupportedEncodingException;
public class TransCodingUtil {
    private final static String ENCODE = "GBK"; 
    /**
     * URL 解码
     * @return String
    
     */
    public static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * URL 转码
     *
     * @return String
     */
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 
     * @return void
     */
    public static void main(String[] args) {
        String str = "oFdssZYYHPzQn9yTckbELx6WN/hH8SvDlilwGL2/M98=";
        System.out.println(getURLEncoderString(str));
        str=getURLEncoderString(str);
        System.out.println(getURLDecoderString(str));
        
    }

}