package com.study.ssm.utils.qrcode;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.EnumMap;

import javax.imageio.ImageIO;

import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
/**
 * 20190428
 * @author limengzhao
 *
 */
public class QRdecode {
    private static final String CHARACTER_SET="utf-8";
    /**
     * 解析二维码图片
     * @param filePath 图片路径
     * @return
     */
    public static String decodeQR(String filePath){
        if("".equalsIgnoreCase (filePath)&&filePath.length ()==0){
            return "二维码图片不存在!";
        }
        
        String content="";
        EnumMap<DecodeHintType, Object> hints = new EnumMap<DecodeHintType, Object>(DecodeHintType.class);
        hints.put (DecodeHintType.CHARACTER_SET , CHARACTER_SET);
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        //复杂模式，开启PURE_BARCODE模式
        hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
        try {
            BufferedImage image = ImageIO.read(new FileInputStream(filePath));
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            MultiFormatReader reader = new MultiFormatReader();
            Result result = reader.decode(binaryBitmap, hints);
            content = result.getText();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return content;
        
    }
    
    
    
    public static void main(String[] args) {
        String filePath="D:/QR/textImage.png";
        String content= decodeQR(filePath);
        System.out.println (content);
    }
    
}
