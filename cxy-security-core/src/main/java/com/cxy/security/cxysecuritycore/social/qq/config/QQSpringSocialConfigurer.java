package com.cxy.security.cxysecuritycore.social.qq.config;

import com.cxy.security.cxysecuritycore.prop.QQProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/***
 * Created by Caoxingyun on 2019/05/29
 */
public class QQSpringSocialConfigurer extends SpringSocialConfigurer {

    @Autowired
    private QQProperties qqProperties;


    //重写父类的postProcess方法,个性化要处理的URL
    @Override
    protected <T> T postProcess(T object) {
        //object
        SocialAuthenticationFilter socialAuthenticationFilter = (SocialAuthenticationFilter) super.postProcess(object);
        //个性化URL
        socialAuthenticationFilter.setFilterProcessesUrl(qqProperties.getAuthUrl());
        return (T) socialAuthenticationFilter;
    }
}
