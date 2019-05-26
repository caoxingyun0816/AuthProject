package com.cxy.security.cxysecuritydemo.filter;

import com.cxy.security.cxysecuritycore.exception.BusinessRunTimeException;
import com.cxy.security.cxysecuritydemo.controller.ImageCodeController;
import com.cxy.security.cxysecuritydemo.exception.ValidateImageException;
import com.cxy.security.cxysecuritydemo.model.ImageCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.PathMatcher;

/***
 * Created by Caoxingyun on 2019/04/19
 * 继承OncePerRequestFilter 保证过滤器只会被调用一次
 */
public class ValidateCodeFilter extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        //登录请求校验验证码
        //可以自定义配置拦截的路径 对于通配符 /user/* 可以有Path
//        AntPathMatcher pathMatcher = new AntPathMatcher();
//        pathMatcher.match("/user/*",request.getRequestURI());
        if (StringUtils.equals("/authentication/loginAction", request.getRequestURI()) &&
                StringUtils.equals("post", request.getMethod())) {
            validImageCode(new ServletWebRequest(request));
        } else {
            filterChain.doFilter(request, response);
        }
    }

    /***
     * 校验图片验证码
     * @param
     */
    private void validImageCode(ServletWebRequest request) throws ServletRequestBindingException {
        //从session中获取图片验证码
        ImageCode imageCode = (ImageCode) sessionStrategy.getAttribute(request, ImageCodeController.imageCodeSession);
        String code = ServletRequestUtils.getStringParameter(request.getRequest(), "code");

        if (StringUtils.isBlank(code)) {
            throw new BusinessRunTimeException("验证码不能为空!");
        }
        if (imageCode == null) {
            throw new BusinessRunTimeException("验证码不存在!");
        }
        if (imageCode.isExpired()) {
            sessionStrategy.removeAttribute(request, ImageCodeController.imageCodeSession);
            throw new BusinessRunTimeException("验证码已过期!");
        }
        if (!StringUtils.equals(imageCode.getCode(), code)) {
            throw new BusinessRunTimeException("验证码不匹配!");
        }
        sessionStrategy.removeAttribute(request, ImageCodeController.imageCodeSession);
    }
}
