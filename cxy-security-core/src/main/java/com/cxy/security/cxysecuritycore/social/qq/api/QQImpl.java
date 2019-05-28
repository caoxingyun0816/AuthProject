package com.cxy.security.cxysecuritycore.social.qq.api;

import com.cxy.security.cxysecuritycore.exception.BusinessRunTimeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;


/***
 * Created by Caoxingyun on 2019/05/27
 * OAuth2 第六步获取用户信息
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    //AbstractOAuth2ApiBinding  accessToken 走完前5步,获取到的权限令牌 AbstractOAuth2ApiBinding是多实例的,没一个用户都会有一个QQImpl实例
    //然后对应自己的accessToken
    //restTemplate 用来获取QQ用户信息

    private static final String GET_OPENID_URL = "https://graph.qq.com/oauth2.0/me?access_token= %s";

    private static final String GET_USER_URL = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;

    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String accessToken, String appId) {
        //父类会将accessToken放到请求参数中
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url = String.format(GET_OPENID_URL, accessToken);
        //获取openId
        String result = this.getRestTemplate().getForObject(url, String.class);
        System.out.println(result);
        this.openId = StringUtils.substringBetween(result, "\"openid\"", "}");

    }

    @Override
    public QQInfo getQQInfo(){
        String url = String.format(GET_USER_URL, appId, openId);
        String result = this.getRestTemplate().getForObject(url, String.class);
        QQInfo qqInfo = null;
        try {
            qqInfo = objectMapper.readValue(result, QQInfo.class);
            qqInfo.setOpenId(openId);
        } catch (Exception e) {
            throw new BusinessRunTimeException("json转换异常!");
        }
        return qqInfo;
    }
}
