package com.study.ssm.utils.qrcode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Hashtable;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
/**
 * 20190428
 * @author limengzhao
 *
 */
public class QRencode {
    
    private static final int BLACK=0xFF000000;
    private static final int WHITE=0xFFFFFFFF;
    private static final int margin =0;
    private static final int LogoPart =4;
    private static final String CHARACTER_SET="UTF-8";
    /**
     * 生成二维码矩阵图
     * @param content
     * @param width
     * @param height
     * @return
     */
    public static BitMatrix setBitMatrix(String content,
            int width,int height){
        Hashtable<EncodeHintType, Object>hints =new Hashtable<EncodeHintType, Object>();
        hints.put (EncodeHintType.CHARACTER_SET , CHARACTER_SET);//设置字符�?
        hints.put (EncodeHintType.ERROR_CORRECTION , ErrorCorrectionLevel.H);//纠错等级
        hints.put (EncodeHintType.MARGIN,margin);//指定二维码四周白色区域的大小 
        BitMatrix bitMatrix = null;
        try {
            bitMatrix=new MultiFormatWriter().encode (content,
                    BarcodeFormat.QR_CODE , width , height,hints);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return bitMatrix;
    }
    /**
     * 生成二维码图�?
     * @param bitMatrix 二维码矩阵信�?
     * @return
     */
    public static BufferedImage toBufferImage(BitMatrix bitMatrix){
        int width = bitMatrix.getWidth ();
        int height = bitMatrix.getHeight ();
        BufferedImage  image=new BufferedImage (width , height , BufferedImage.TYPE_3BYTE_BGR);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB (x , y , bitMatrix.get (x , y)?BLACK:WHITE);
            }
            
        }
        return image;
    }
    /**
     * 在二维码图片中添加logo
     * @param image 二维码图�?
     * @param logoPath logo路径
     * @return
     * @throws IOException 
     */
    public static BufferedImage imageAddLogo(BufferedImage image,String logoPath) throws IOException{
        Graphics2D g=image.createGraphics ();
        BufferedImage logoImage=ImageIO.read (new File (logoPath));
        // 计算logo图片大小,可�?�应长方形图�?,根据较短边生成正方形
        int width=image.getWidth ()<image.getHeight ()?image.getWidth ()/LogoPart:image.getHeight() / LogoPart;
        int height = width;
        // 计算logo图片放置位置
        int x = (image.getWidth() - width) / 2;
        int y = (image.getHeight() - height) / 2;
        // 在二维码图片上绘制logo图片
        g.drawImage (logoImage , x , y , width,height,null);
        // 绘制logo边框,可�??
        // g.drawRoundRect(x, y, logoImage.getWidth(), logoImage.getHeight(), 10, 10);
        g.setStroke (new BasicStroke (2));//// 画笔粗细
        g.setColor (Color.WHITE);//边框颜色
        g.drawRect (x , y , width , height);//矩形边框
        logoImage.flush ();
        g.dispose ();
        return image;
    }
    /**
     * 为图片添加文�?
     * @param pressText 文字
     * @param newImage 带文字的图片
     * @param targetImage �?要添加文字的图片
     * @param fontStyle 字体风格
     * @param color 字体颜色
     * @param fontSize 字体大小
     * @param width 图片宽度
     * @param height 图片高度
     */
    public static void pressText(String pressText,String newImage,
            String targetImage,int fontStyle,
            Color color,int fontSize,int width,int height){
        // 计算文字�?始的位置
        // x�?始的位置：（图片宽度-字体大小*字的个数�?/2
        int startX = (width-(fontSize*pressText.length()))/2;
        // y�?始的位置：图片高�?-（图片高�?-图片宽度�?/2
        int startY = height-(height-width)/2 + fontSize;
        try {
            File file=new File (targetImage);
            BufferedImage src = ImageIO.read(file);
            int imageW = src.getWidth(null);
            int imageH = src.getHeight(null);
            BufferedImage image = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, imageW, imageH, null);
            g.setColor(color);
            g.setFont(new Font(null, fontStyle, fontSize));
            g.drawString(pressText, startX, startY);
            g.dispose();
            FileOutputStream out = new FileOutputStream(newImage);
            ImageIO.write(image, "png", out);
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 将二维码输出
     * @param matrix 二维码矩阵信�?
     * @param format 图片格式
     * @param outStream 输出�?
     * @param logoPath logo图片路径
     * @throws IOException 
     */
    public static void writeToImageFile(BitMatrix matrix, String format, OutputStream outStream, String logoPath) throws IOException{
        BufferedImage image = toBufferImage(matrix);
        // 加入LOGO水印效果
        if(StringUtils.isNotBlank(logoPath)){
            image=imageAddLogo(image,logoPath);
        }
        ImageIO.write (image , format , outStream);
        
    }
    
    
    public static void main(String[] args) {
        String content="http://www.baidu.com";
        String logoPath = "C:/Users/2ligang/Desktop/5.jpg";
        String format = "jpg";
        int width = 160;
        int height = 160;
        BitMatrix bitMatrix = setBitMatrix(content, width, height);
        // 可�?�过输出流输出到页面,也可直接保存到文�?
        OutputStream outStream = null;
        String path = "d:/QR/qr"+new Date().getTime()+".jpg";
        try {
            outStream = new FileOutputStream(new File(path));        
            writeToImageFile(bitMatrix, format, outStream, logoPath);
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
     // 添加文字效果
        int fontSize = 12; // 字体大小
        int fontStyle = 1; // 字体风格
        String text = "测试二维�?";
        String withTextPath = "d:/QR/textImage.png";
        pressText(text, withTextPath, path, fontStyle, Color.BLUE, fontSize, width, height);
        
    }
    
}
