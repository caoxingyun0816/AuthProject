package com.cxy.security.cxysecuritydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/***
 * Created by Caoxingyun on 2019/03/26
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    //用户密码加密和匹配
//    String encode(CharSequence var1);

//    boolean matches(CharSequence var1, String var2);匹配用户输入的密码和数据库的密码是否一致
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        //可以自定义实现类实现接口即可用自己的方法加解密
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.formLogin()//表单登录
                //http.httpBasic() 默认登录
        .and().authorizeRequests().anyRequest().authenticated();//任何请求都需要验证
    }
}
