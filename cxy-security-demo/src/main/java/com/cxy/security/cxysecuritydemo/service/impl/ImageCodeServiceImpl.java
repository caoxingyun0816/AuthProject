package com.cxy.security.cxysecuritydemo.service.impl;

import com.cxy.security.cxysecuritydemo.config.ImageCodeConfig;
import com.cxy.security.cxysecuritydemo.util.ImageUtil;
import com.cxy.security.cxysecuritydemo.model.ImageCode;

import javax.servlet.http.HttpServletRequest;

/***
 * Created by Caoxingyun on 2019/04/11
 */
//@Service("imageCodeService")
public class ImageCodeServiceImpl implements ImageCodeService {

    @Override
    public ImageCode createImageCode(HttpServletRequest request) {
        ImageCode imageCode = null;
        ImageUtil image = new ImageUtil();
        imageCode.setCode(image.getCode());
        imageCode.setImage(image.getBufferedImage());
        return imageCode;

    }
}
