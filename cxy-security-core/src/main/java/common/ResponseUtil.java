package common;

import exception.BusinessRunTimeException;
import model.ErrorCode;
import model.ErrorCodeInterface;
import model.ResponseVO;

public class ResponseUtil {

    public static <T> ResponseVO<T> getResponseVO() {
        ResponseVO<T> response = new ResponseVO<T>();
        return response;
    }

    /**
     * 正确返回
     *
     * @param <T>
     * @return
     */
    public static <T> ResponseVO<T> ok() {
        return ok((T) null);
    }

    public static <T> ResponseVO<T> ok(T data) {
        ResponseVO<T> response = null;
        if (data != null) {
            response = new ResponseVO<T>(data, ErrorCode.SUCCESS);
        } else {
            response = new ResponseVO<T>(null, ErrorCode.SUCCESS);
        }
        return response;
    }

    public static <T> ResponseVO<T> ok(T data, String mes) {
        ResponseVO<T> response = null;
        if (data != null) {
            response = new ResponseVO<T>(data, mes);
        } else {
            response = new ResponseVO<T>(null, mes);
        }
        return response;
    }

    /**
     * 错误返回
     *
     * @return
     */
    public static <T> ResponseVO<T> error() {
        ResponseVO<T> response = new ResponseVO<T>(null);
        return response;
    }

    public static <T> ResponseVO<T> error(String message) {
        ResponseVO<T> response = new ResponseVO<T>(message);
        return response;
    }

    public static <T> ResponseVO<T> error(int code, String message) {
        ResponseVO<T> response = new ResponseVO<T>(code, message);
        return response;
    }

    public static <T> ResponseVO<T> error(ErrorCodeInterface errorCode) {
        return error(errorCode, null);
    }

    public static <T> ResponseVO<T> error(ErrorCodeInterface errorCode, Object key, Object value) {
        if (key == null) {
            return error(errorCode);
        }
        if (value == null) {
            value = "";
        }
        return (ResponseVO<T>) error(errorCode, String.format("%s:%s", key, value));
    }

    public static <T> ResponseVO<T> error(ErrorCodeInterface errorCode, T data) {
        if (errorCode == null) {
            errorCode = ErrorCode.ERROR;
        }
        return new ResponseVO<T>(data, errorCode);
    }

    public static void handleReponseErrorThrow(ResponseVO responseVO) {
        if (responseVO.getCode() != 0) {
            throw new BusinessRunTimeException(responseVO.getMessage(), responseVO.getCode());
        }
    }

    public static boolean isSuccess(ResponseVO responseVO) {
        if (responseVO.getCode() == 0) {
            return true;
        }
        return false;
    }
}
