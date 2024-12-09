package com.wrx.common.exception;

import lombok.Data;

@Data
public class BizException extends RuntimeException{

	private String msg;
	private int code = 500;

	public BizException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public BizException(int code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}
}
