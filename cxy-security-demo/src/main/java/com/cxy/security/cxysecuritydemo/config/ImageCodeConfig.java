package com.cxy.security.cxysecuritydemo.config;

import com.cxy.security.cxysecuritydemo.service.impl.ImageCodeService;
import com.cxy.security.cxysecuritydemo.service.impl.ImageCodeServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * Created by Caoxingyun on 2019/05/06
 */
@Configuration
public class ImageCodeConfig {

    //当没有自定义的imageCodeService时,会自动创建imageCodeService
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeService")
    public ImageCodeService imageCodeService(){
        return new ImageCodeServiceImpl();
    }

}
