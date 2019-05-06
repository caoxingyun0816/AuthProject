package com.cxy.security.cxysecuritydemo.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/***
 * Created by Caoxingyun on 2019/04/18
 */
public class ImageUtil {

    //图片的宽度
    private int width = 160;

    //图片的高度
    private int height = 40;

    //验证码长度
    private int codeCount = 4;

    //验证码干扰线数
    private int lineCount = 150;

    //验证码
    private String code = null;

    //验证码图片
    private BufferedImage bufferedImage = null;

    //验证码范围 去掉0(数字)和O(拼音)容易混淆的(小写的1和L也可以去掉,大写不用了)
    private char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    /**
     * 默认构造函数,设置默认参数
     */
    public ImageUtil() {
        this.createImage();
    }

    /**
     * @param width  图片宽
     * @param height 图片高
     */
    public ImageUtil(int width, int height) {
        this.width = width;
        this.height = height;
        this.createImage();
    }

    /**
     * @param width     图片宽
     * @param height    图片高
     * @param codeCount 字符个数
     * @param lineCount 干扰线条数
     */
    public ImageUtil(int width, int height, int codeCount, int lineCount) {
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        this.lineCount = lineCount;
        this.createImage();
    }

    /***
     * 创建图片
     */
    private void createImage() {
        int x = 0, fontHeight = 0, codeY = 0;
        int red = 0, green = 0, blue = 0;
        //每个字符的宽度(左右各空出一个字符)
        x = width / (codeCount + 2);
        //字体的高度
        fontHeight = height -2 ;
        codeY = height - 4;
        //图像buffer
        bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();
        //生成随机数
        Random random = new Random();
        //将图片填充白色
        g.setColor(Color.WHITE);
        //进行轮廓的绘制，后续的绘制范围不会超过这个轮廓
        g.fillRect(0,0,width,height);
        //创建字体
        Font font = new Font("Fixedsys",Font.PLAIN, fontHeight);
        g.setFont(font);
        for (int i = 0; i < lineCount; i++) {
            //设置随机开始和结束坐标
            //开始坐标
            int xs = random.nextInt(height);
            int ys = random.nextInt(width);
            //结束坐标
            int xe = xs + random.nextInt(height/8);
            int ye = ys + random.nextInt(width/8);
            //产生随机颜色
            red = random.nextInt(255);
            blue = random.nextInt(255);
            green = random.nextInt(255);
            g.setColor(new Color(red,blue,green));
            g.drawLine(xs,ys,xe,ye);
        }
        //记录随机产生的验证码
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < codeCount; i++) {
            String charString = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            //随机颜色
            g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
            //绘制字符
            g.drawString(charString,(i + 1)*x,codeY);
            stringBuffer.append(charString);
        }
        code = stringBuffer.toString();
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public String getCode() {
        return code;
    }


}
