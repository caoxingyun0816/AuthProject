package com.cxy.security.cxysecuritycore;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/***
 * Created by Caoxingyun on 2019/05/28
 */
@Component
@Data
public class QQProp {

//    @Value("${QQ.openIdUrl}")
//    private String openIdUrl;
//
//    @Value("${QQ.tokenAccessUrl}")
//    private String tokenAccessUrl;
//
//    @Value("${QQ.authorizationCodeUrl}")
//    private String authorizationCodeUrl;

    @Value("${QQ.APP_ID}")
    private String appId;

    @Value("${QQ.AUTHORIZE_URL}")
    private String authorizeUrl;

    @Value("${QQ.ACCESS_TOKEN_URL}")
    private String accessTokenUrl;
}
