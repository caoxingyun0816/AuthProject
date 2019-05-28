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
     */
    public QQConnectionFactory(String providerId, String appId, String appKey) {
        super(providerId, new QQServiceProvider(appId, appKey), new QQAdapter());
    }
}
