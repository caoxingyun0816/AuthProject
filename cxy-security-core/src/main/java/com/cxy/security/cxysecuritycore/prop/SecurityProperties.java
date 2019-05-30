package com.cxy.security.cxysecuritycore.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/***
 * Created by Caoxingyun on 2019/05/29
 */
@Data
@Component
@ConfigurationProperties(prefix = "QQ")
public class SecurityProperties {

    private QQProperties qqProperties = new QQProperties();
}
