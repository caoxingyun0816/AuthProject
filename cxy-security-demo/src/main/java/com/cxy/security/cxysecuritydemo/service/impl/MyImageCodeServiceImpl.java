package com.cxy.security.cxysecuritydemo.service.impl;

import com.cxy.security.cxysecuritydemo.config.ImageCodeConfig;
import com.cxy.security.cxysecuritydemo.model.ImageCode;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/***
 * Created by Caoxingyun on 2019/05/06
 */
@Service("imageCodeService")
public class MyImageCodeServiceImpl implements ImageCodeService{

    /***
     * {@link ImageCodeConfig}
     * @param request
     * @return
     */
    @Override
    public ImageCode createImageCode(HttpServletRequest request) {
        System.out.println("自定义的测试,自己配置相关的实现类,如果没有配置系统会使用自己默认的实现类");
        //所以就可以自己实现spring框架中对应的类,实现自己的功能
        return null;
    }
}
