package com.cxy.security.cxysecuritydemo.controller;

import com.cxy.security.cxysecuritycore.common.ResponseUtil;
import com.cxy.security.cxysecuritycore.model.ResponseVO;
import com.cxy.security.cxysecuritydemo.model.SpringSocailUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/***
 * Created by Caoxingyun on 2019/03/27
 */
@RestController
@RequestMapping("/authentication")
public class BrowserSecurityController {

    @Value("${security.browser.loginPage}")
    private String loginPage;

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //该类可以获得请求的具体信息
    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    //一般都是前后端分离的项目，不需要这样的判断
    @PostMapping("/login")
    public ResponseVO requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //拿到引发跳转登录的请求
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求:" + targetUrl);
            // 引发跳转的请求是html则将请求跳转到登陆页
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
                redirectStrategy.sendRedirect(request, response, loginPage);
            }
        }
        return ResponseUtil.error(1000,"访问的服务需要身份验证，请跳转到登录页。");
    }

    @GetMapping("/user/social")
    public SpringSocailUser getSpringSocialUser(HttpServletRequest request){
        //从session中拿去social user 信息
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        SpringSocailUser springSocailUser = new SpringSocailUser();
        springSocailUser.setImgUrl(connection.getImageUrl());
        springSocailUser.setNickName(connection.getDisplayName());
        springSocailUser.setProvideAccessId(connection.getKey().getProviderUserId());
        springSocailUser.setProvideId(connection.getKey().getProviderId());
        return springSocailUser;
    }


}
