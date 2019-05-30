package com.cxy.security.cxysecuritycore.social.qq.connection;

import com.cxy.security.cxysecuritycore.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/***
 * Created by Caoxingyun on 2019/05/28
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {
    /**
     * Create a {@link OAuth2ConnectionFactory}.
     *
     * @param providerId the provider id e.g. "facebook"
     *  //QQConnectionFactory 创建 QQServiceProvider 和 QQAdapter 完成OAuth2全部步骤
     */
    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}
