package com.cxy.security.cxysecuritycore.prop;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/***
 * Created by Caoxingyun on 2019/05/29
 */
@Data
public class QQProperties extends SocialProperties {

    //服务提供商的标识
    private String provideId;

    //注册URL
    private String signUpUrl;

    private String authUrl;
}
