package com.cxy.security.cxysecuritycore.common;

import com.cxy.security.cxysecuritycore.exception.BusinessRunTimeException;
import com.cxy.security.cxysecuritycore.model.ErrorCode;
import com.cxy.security.cxysecuritycore.model.ResponseVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 基础控制器
 */
public abstract class BaseController {
    /**
     * 统一异常处理
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public ResponseVO exceptionHandler(HttpServletRequest request, Exception ex) {
        if (ex instanceof BusinessRunTimeException) {
            if (((BusinessRunTimeException) ex).getErrorCode() != null) {
                BusinessRunTimeException srte = (BusinessRunTimeException) ex;
                return ResponseUtil.error(srte.getErrorCode());
            } else if (((BusinessRunTimeException) ex).getCode() != null) {
                BusinessRunTimeException srte = (BusinessRunTimeException) ex;
                return ResponseUtil.error(srte.getCode(), srte.getMessage());
            }
            return ResponseUtil.error(ex.getMessage());
        } else if (ex instanceof MethodArgumentNotValidException) {
            String defaultMessage = ((MethodArgumentNotValidException) ex).getBindingResult().getFieldError().getDefaultMessage();
            if (StringUtils.isNotBlank(defaultMessage)) {
                return ResponseUtil.error(ErrorCode.WRONG_PARAM.getCode(), defaultMessage);
            }
            return ResponseUtil.error(ErrorCode.WRONG_PARAM);
        }
        return ResponseUtil.error(ErrorCode.WRONG_ONSERVER);
    }

    public String getUserId() {
        try {
            String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            if ("APP".equals(userName)) {
                return null;
            }
            return userName;
        } catch (Exception e) {
            return null;
        }
    }

//    public String getSource() {
//        try {
//            String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
//            if ("APP".equals(userName)) {
//                Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//                Object[] objects = authorities.toArray();
//                return objects[0].toString();
//            }
//            return DataSourceEnum.ZT.getCode();
//        } catch (Exception e) {
//            return DataSourceEnum.ZT.getCode();
//        }
//    }
}
