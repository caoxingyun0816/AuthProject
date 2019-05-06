package com.cxy.security.cxysecuritydemo.exception;

import javax.naming.AuthenticationException;

/***
 * Created by Caoxingyun on 2019/04/22
 * AuthenticationException 所有身份认证异常的基类
 */
public class ValidateImageException extends AuthenticationException {

    public ValidateImageException(String msg){
        super(msg);
    }
}
