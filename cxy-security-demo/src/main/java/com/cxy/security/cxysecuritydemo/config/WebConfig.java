package com.cxy.security.cxysecuritydemo.config;

import com.cxy.security.cxysecuritydemo.Interceptor.TimeInterceptor;
import com.cxy.security.cxysecuritydemo.filter.TimeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/***
 * Created by Caoxingyun on 2019/03/15
 * 何将第三方中没用component注解的filter加入到springboot的项目中的过滤器链上
 * 配置类
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private TimeInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(timeInterceptor);
    }

    // bean配置类似于在web.xml中手动配置filter
//
//    <filter-mapping>
//        <filter-name>Test2Filter</filter-name>
//        <url-pattern>/*</url-pattern>
//    </filter-mapping>
    @Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        filterRegistrationBean.setFilter(timeFilter);
        //配置过滤器的拦截路径
        List<String> urls = new ArrayList<>();
        urls.add("*");
        filterRegistrationBean.setUrlPatterns(urls);
        return filterRegistrationBean;
    }
}
