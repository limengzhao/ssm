package com.study.ssm.utils.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SHA256Util {
	
	 private static final String HMAC_SHA256 = "HmacSHA256";
	
	
	public static String getSHA256Hex(String str){
	 MessageDigest messageDigest;
	 String encodeStr = "";
	 try {
	  messageDigest = MessageDigest.getInstance("SHA-256");
	  messageDigest.update(str.getBytes("UTF-8"));
	  encodeStr = byte2Hex(messageDigest.digest());
	 } catch (NoSuchAlgorithmException e) {
	  e.printStackTrace();
	 } catch (UnsupportedEncodingException e) {
	  e.printStackTrace();
	 }
	 return encodeStr;
	}
	/**
	* 将byte转为16进制
	* @param bytes
	* @return
	*/
	private static String byte2Hex(byte[] bytes){
	 StringBuffer stringBuffer = new StringBuffer();
	 String temp = null;
	 for (int i=0;i<bytes.length;i++){
	  temp = Integer.toHexString(bytes[i] & 0xff);
	  if (temp.length()==1){
	  //1得到�?位的进行�?0操作 
	  stringBuffer.append("0");
	  }
	  stringBuffer.append(temp);
	 }
	 return stringBuffer.toString();
	}
	
	
	
	
	public static String sign(HashMap<String, Object> params, String appKey) {
        StringBuilder valueSb = new StringBuilder();
        // 将参数以参数名的字典升序排序
        Map<String, Object> sortParams = new TreeMap<String, Object>(params);
        Set<Map.Entry<String, Object>> entrys = sortParams.entrySet();
        // 遍历排序的字�?,并拼接key=value&key=......格式
        for (Map.Entry<String, Object> entry : entrys) {
            valueSb.append(entry.getKey()+"="+entry.getValue()+"&");
        }
        //把私钥拼接在数据后面
        String signature = valueSb.toString()+"appKey="+appKey;//注意私钥使用了两次，valueSb中也�?
        System.out.println("待签名传"+signature);
        String digestHex = getSHA256Hex(signature);
        return digestHex;
    }
	
	
	/**
     * 生成签名数据_HmacSHA256加密
     *
     * @param data
     *            待加密的数据
     * @param key
     *            加密使用的key
     */
    public static String getSignature(String data, String key) throws Exception {

        byte[] keyBytes = key.getBytes();
        // 根据给定的字节数组构造一个密钥�??
        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_SHA256);
        Mac mac = Mac.getInstance(HMAC_SHA256);
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(data.getBytes());

        String hexBytes = byte2hex(rawHmac);
        return hexBytes;
    }
    
    
    private static String byte2hex(final byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            // 以十六进制（基数 16）无符号整数形式返回�?个整数参数的字符串表示形式�??
            stmp = (Integer.toHexString(b[n] & 0xFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs;
    }
	
	
	public static void main(String[] args) {
		String string="加解密测�?";
		HashMap<String, Object> params=new HashMap<String, Object>();
		params.put("name", "nnli");
		String appKey="aaaaaaaaaa";
		System.out.println(sign(params,appKey));
	}
}