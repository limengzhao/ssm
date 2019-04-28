package com.study.ssm.utils.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;



public class ThreeDesc {
    private static final String Algorithm = "DESede";
    
    /**
     * 加密数据
     * @param keyByte
     * @param src
     * @return
     */
    public static String encryptEncode(byte[] keyByte,String text){
        try {
            SecretKey desKey=new SecretKeySpec(keyByte,Algorithm);
            Cipher cipher=Cipher.getInstance (Algorithm+"/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, desKey);  
            text=TransCodingUtil.getURLDecoderString(text);
            byte[] encryptData = cipher.doFinal(text.getBytes("UTF-8"));
            return TransCodingUtil.getURLEncoderString(Base64.encode(encryptData));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    
    /**
     * 解密数据
     * @param keyByte
     * @param src
     * @return
     */
    public static String decryptDecode(byte[] keyByte,String encryptData){
        SecretKey desKey=new SecretKeySpec(keyByte,Algorithm);
        try {
            Cipher cipher=Cipher.getInstance (Algorithm+"/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, desKey);  
            encryptData=TransCodingUtil.getURLDecoderString(encryptData);
            byte[] decryptData = cipher.doFinal(Base64.decode(encryptData));
            return new String(decryptData, "UTF-8").trim();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return null;
    }
    /**
    * 根据字符串生成密钥字节数�? 
    * @param keyStr 密钥字符�?
    * @return 
    * @throws UnsupportedEncodingException
    */
   public static byte[] build3DesKey(String keyStr) throws UnsupportedEncodingException{
       byte[] key = new byte[24];    //声明�?�?24位的字节数组，默认里面都�?0
       byte[] temp = keyStr.getBytes("UTF-8");    //将字符串转成字节数组
       if(key.length > temp.length){
           System.arraycopy(temp, 0, key, 0, temp.length);
       }else{
           System.arraycopy(temp, 0, key, 0, key.length);
       }
       return key;
   } 

    
}
