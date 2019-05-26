package com.cxy.security.cxysecuritydemo.config;

import com.cxy.security.cxysecuritydemo.filter.ValidateCodeFilter;
import com.cxy.security.cxysecuritydemo.service.impl.MyAuthenticationFailureHandler;
import com.cxy.security.cxysecuritydemo.service.impl.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

//import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/***
 * Created by Caoxingyun on 2019/03/26
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${security.browser.loginPage}")
    private String loginPage;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    //用户密码加密和匹配
    //String encode(CharSequence var1);
    //boolean matches(CharSequence var1, String var2);匹配用户输入的密码和数据库的密码是否一致
    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        //可以自定义实现类实现接口即可用自己的方法加解密
    }

    //tokenRepository用来将用户信息存储到数据库 记住身份信息是有一个时间的限制的,配置对应的token过期的时间
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        tokenRepository.setCreateTableOnStartup(true);//启动时会自动创建表 一个username和token的映射表
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        //在用户密码验证过滤器前加入自定义的图片验证码过滤器
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class).formLogin()
                .loginPage("/authentication/login")//登录成功跳转路径
                .loginProcessingUrl("/authentication/loginAction")//登录请求路径
                .successHandler(myAuthenticationSuccessHandler)//登陆成功后用自定义handler处理请求
                .failureHandler(myAuthenticationFailureHandler)//登陆失败后用自定义handler处理请求
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())//设置tokenRepository
                .tokenValiditySeconds(3600*24*30)//过期的时间 一个月
                .userDetailsService(userDetailsService)
                //http.httpBasic() 默认登录
                .and().authorizeRequests()
                .antMatchers("/authentication/login", loginPage, "/imageCode/imageCode").permitAll()//允许该路径访问
                .anyRequest().authenticated()//其它任何请求都需要验证
                .and()
                .csrf().disable();//关闭跨域访问
    }
}
