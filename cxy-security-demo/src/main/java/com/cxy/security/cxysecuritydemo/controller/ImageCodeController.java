package com.cxy.security.cxysecuritydemo.controller;

import com.cxy.security.cxysecuritydemo.service.impl.ImageCodeService;
import com.cxy.security.cxysecuritydemo.model.ImageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/***
 * Created by Caoxingyun on 2019/04/11
 */


@RestController
@RequestMapping("/imageCode")
public class ImageCodeController {

    public final static String imageCodeSession = "IMAGE_CODE_SESSION";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ImageCodeService imageCodeService;

    @PostMapping("/imageCode")
    public void getImageCode(HttpServletRequest request, HttpServletResponse response){
        //设置响应为图片格式
        response.setContentType("image/jpeg");
        response.setHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        ImageCode imageCode = imageCodeService.createImageCode(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request),imageCodeSession,imageCode);
        try {
            OutputStream out = response.getOutputStream();
            ImageIO.write(imageCode.getImage(),"JPEG",out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
