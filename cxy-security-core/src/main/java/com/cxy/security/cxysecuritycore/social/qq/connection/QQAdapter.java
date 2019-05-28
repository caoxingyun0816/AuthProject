package com.cxy.security.cxysecuritycore.social.qq.connection;

import com.cxy.security.cxysecuritycore.social.qq.api.QQ;
import com.cxy.security.cxysecuritycore.social.qq.api.QQInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/***
 * Created by Caoxingyun on 2019/05/28
 */
public class QQAdapter implements ApiAdapter<QQ> {

    //测试连接是否有效
    @Override
    public boolean test(QQ api) {
        return true;
    }


    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQInfo qqInfo = api.getQQInfo();
        values.setDisplayName(qqInfo.getNickname());
        values.setImageUrl(qqInfo.getFigureurl_qq_1());
        //主页
        values.setProfileUrl(null);
        //openid
        values.setProviderUserId(qqInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {

    }
}
