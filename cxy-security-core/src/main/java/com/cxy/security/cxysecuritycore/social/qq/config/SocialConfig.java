package com.cxy.security.cxysecuritycore.social.qq.config;

import com.cxy.security.cxysecuritycore.prop.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/***
 * Created by Caoxingyun on 2019/05/28
 * ConnectionRepository
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired(required = false)
    private DemoConnectionSignUp demoConnectionSignUp;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository usersConnectionRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        usersConnectionRepository.setTablePrefix("cxy_");//表的前缀 将第三方用户信息持久化都表中
        if (null != demoConnectionSignUp) {
            usersConnectionRepository.setConnectionSignUp(demoConnectionSignUp);
        }
        return usersConnectionRepository;
    }

    //SpringSocialConfigurer springSocial 的过滤器
    @Bean
    public SpringSocialConfigurer springSocialConfigurer() {
        QQSpringSocialConfigurer qqSpringSocialConfigurer = new QQSpringSocialConfigurer();
        //自定义注册url
        qqSpringSocialConfigurer.signupUrl(securityProperties.getQqProperties().getSignUpUrl());
        return qqSpringSocialConfigurer;
    }

    //用来处理 注册绑定 userID SpringSocial 将用户id和第三方用户信息绑定，并且将信息存到数据库中
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator){
        return new ProviderSignInUtils(connectionFactoryLocator,getUsersConnectionRepository(connectionFactoryLocator));
    }
}
