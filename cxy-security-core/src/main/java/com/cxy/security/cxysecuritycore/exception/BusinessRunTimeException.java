package com.cxy.security.cxysecuritycore.exception;

import com.cxy.security.cxysecuritycore.model.ErrorCodeInterface;

/***
 *
 */
public class BusinessRunTimeException extends RuntimeException {

    private ErrorCodeInterface errorCode;

    private Integer code;

    /**
     * 自定义业务层异常
     */
    private static final long serialVersionUID = 3297894301127542570L;

    public BusinessRunTimeException() {
        super();
    }

    public BusinessRunTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BusinessRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessRunTimeException(String message) {
        super(message);
    }

    public BusinessRunTimeException(Throwable cause) {
        super(cause);
    }

    public BusinessRunTimeException(String message, int code) {
        super(message);
        this.code = code;
    }

    public BusinessRunTimeException(ErrorCodeInterface errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public Integer getCode() {
        return code;
    }

    public ErrorCodeInterface getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCodeInterface errorCode) {
        this.errorCode = errorCode;
    }
}
