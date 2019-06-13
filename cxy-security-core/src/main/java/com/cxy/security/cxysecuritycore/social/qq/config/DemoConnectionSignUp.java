package com.cxy.security.cxysecuritycore.social.qq.config;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/***
 * Created by Caoxingyun on 2019/06/13
 */
@Component
public class DemoConnectionSignUp implements ConnectionSignUp {

    //用户第三方登录时直接默认创建用户并绑定第三方信息，不用界面上用户手动绑定
    @Override
    public String execute(Connection<?> connection) {
        //根据第三方信息创建默认用户
        return connection.getDisplayName();
    }
}
