package com.cxy.security.cxysecuritycore.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

@Data
public class ResponseVO<T> {

	@ApiModelProperty(value = "返回的状态code:0表示成功,其他表示错误")
	private int code;
	@ApiModelProperty(value = "返回的附加信息")
	private String message;
	@ApiModelProperty(value = "返回的对象内容")
	private T data;

	public ResponseVO() {
		this.code = ErrorCode.SUCCESS.getCode();
		this.message = ErrorCode.SUCCESS.getMessage();
	}

	public ResponseVO(int code, String message) {
		this.code = code;
		this.message = message;
	}

//	static Response(Object data) {
//		this(data, null);
//	}
//	static Response(Object data,String mes) {
//		this(data, null);
//	}

	/**
	 * 正确返回
	 *
	 * @param data
	 * @param errorCode
	 */
	public ResponseVO(T data, ErrorCodeInterface errorCode) {
		this.data = data;
		if (errorCode == null) {
			this.code = ErrorCode.SUCCESS.getCode();
		} else {
			this.code = errorCode.getCode();
			this.message = errorCode.getMessage();
		}
	}
	public ResponseVO(T data, String mes) {
		this.data = data;
		this.code = 0;
		if(StringUtils.isNotEmpty(mes)){
			this.message = mes;
		}
	}

	/**
	 * 错误返回
	 *
	 * @param message
	 */
	public ResponseVO(String message) {
		this.data = null;
		if (message == null) {
			this.code = ErrorCode.ERROR.getCode();
			this.message = ErrorCode.ERROR.getMessage();
		} else {
			this.code = ErrorCode.ERROR.getCode();
			this.message = message;
		}
	}

	public void setErrorCode(ErrorCodeInterface errorCode) {
		if (errorCode == null) {
			this.code = ErrorCode.SUCCESS.getCode();
			this.message = ErrorCode.SUCCESS.getMessage();
		} else {
			this.code = errorCode.getCode();
			this.message = errorCode.getMessage();
		}
	}
}
