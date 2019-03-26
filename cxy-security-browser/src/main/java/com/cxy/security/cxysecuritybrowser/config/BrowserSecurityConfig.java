//package com.cxy.security.cxysecuritybrowser.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
////import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
///***
// * Created by Caoxingyun on 2019/03/26
// */
//@Configuration
//public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.formLogin()//表单登录
//                //http.httpBasic() 默认登录
//        .and().authorizeRequests().anyRequest().authenticated();//任何请求都需要验证
//    }
//}
