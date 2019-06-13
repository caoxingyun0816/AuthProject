package com.cxy.security.cxysecuritycore.social.qq.connection;

import com.cxy.security.cxysecuritycore.social.qq.api.QQ;
import com.cxy.security.cxysecuritycore.social.qq.api.QQImpl;
import com.cxy.security.cxysecuritycore.social.qq.config.QQOAuth2Template;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/***
 * ServiceProvider 完成认证的1-6步
 * Created by Caoxingyun on 2019/05/28
 * 泛型是API接口的类型
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    //appId
    private String appId;

    private final static String accessTokenUrl = "https://graph.qq.com/oauth2.0/token";

    private final static String authorizeUrl = "https://graph.qq.com/oauth2.0/authorize";

    //AbstractOAuth1ServiceProvider 获取授权码和令牌
    public QQServiceProvider(String appId, String appSecret) {
        super(new QQOAuth2Template(appId, appSecret, authorizeUrl, accessTokenUrl));
        this.appId = appId;
    }

    //API AbstractOAuth2ApiBinding 获取用户信息
    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }

}
