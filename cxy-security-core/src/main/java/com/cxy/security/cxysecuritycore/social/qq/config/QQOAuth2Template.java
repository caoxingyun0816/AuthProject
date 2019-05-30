package com.cxy.security.cxysecuritycore.social.qq.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Map;

/***
 * Created by Caoxingyun on 2019/05/30
 * 自定义template
 */
public class QQOAuth2Template extends OAuth2Template {

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
    }

    //    处理返回类型为text/html的
    //返回说明：
    //1. 如果用户成功登录并授权，则会跳转到指定的回调地址，并在redirect_uri地址后带上Authorization Code和原始的state值。如：
    //PC网站：http://graph.qq.com/demo/index.jsp?code=9A5F************************06AF&state=test
    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

    //QQ返回的认证信息是不是json需要自定义处理
    //如果成功返回，即可在返回包中获取到Access Token。 如：
    //access_token=FE04************************CCE2&expires_in=7776000&refresh_token=88E4************************BE14
    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String res = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
        String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(res, "&");
        String accessToken = StringUtils.substringAfterLast(items[0], "=");
        Long expireIn = new Long(StringUtils.substringAfterLast(items[0], "="));
        String reFreshToken = StringUtils.substringAfterLast(items[0], "=");
        return new AccessGrant(accessToken,null,reFreshToken,expireIn);
    }
}
