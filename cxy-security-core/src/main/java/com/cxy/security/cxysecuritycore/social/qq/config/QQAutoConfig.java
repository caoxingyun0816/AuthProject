package com.cxy.security.cxysecuritycore.social.qq.config;

import com.cxy.security.cxysecuritycore.prop.SecurityProperties;
import com.cxy.security.cxysecuritycore.social.qq.connection.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/***
 * QQ的连接工厂
 * Created by Caoxingyun on 2019/05/29
 */
@Configuration
@ConditionalOnProperty(prefix = "QQ ", name = "QQ.appId")//当配置文件中有QQ.appId 才自动配置该类
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        return new QQConnectionFactory(securityProperties.getQqProperties().getProvideId(), securityProperties.getQqProperties().getAppId(), securityProperties.getQqProperties().getAppSecret());
    }
}
