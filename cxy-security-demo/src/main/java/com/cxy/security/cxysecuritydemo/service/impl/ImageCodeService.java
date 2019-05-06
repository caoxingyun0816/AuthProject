package com.cxy.security.cxysecuritydemo.service.impl;

import com.cxy.security.cxysecuritydemo.model.ImageCode;

import javax.servlet.http.HttpServletRequest;

/***
 * Created by Caoxingyun on 2019/04/11
 */
public interface ImageCodeService {

    /***
     * 创建验证图片
     * @param request
     * @return
     */
    ImageCode createImageCode(HttpServletRequest request);
}
