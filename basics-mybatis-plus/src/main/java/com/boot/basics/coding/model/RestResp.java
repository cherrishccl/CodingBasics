package com.boot.basics.coding.model;

import java.io.Serializable;

/**
 * @Author cherrishccl
 * @Date 2021/04/28 22:39
 * @Version: 1.0
 * @Description 统一返回
 */
public class RestResp<T> implements Serializable {
	private static final long serialVersionUID = -5079874555047290636L;

	private static final int CODE_SUCCESS = 200;
	private static final int CODE_FAIL = 500;
	private static final String MSG_SUCCESS = "SUCCESS";
	private static final String MSG_FAIL = "FAIL";
	public final int code;
	public final String message;
	public final T data;

	public final Long currentTime;

	protected RestResp(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
		this.currentTime = System.currentTimeMillis();
	}

	public static <T> RestResp<T> success(T data) {
		return new RestResp(CODE_SUCCESS, MSG_SUCCESS, data);
	}

	public static RestResp success() {
		return new RestResp(CODE_SUCCESS, MSG_SUCCESS, null);
	}

	public static <T> RestResp<T> success(String message, T data) {
		return new RestResp(CODE_SUCCESS, message, data);
	}

	public static RestResp fail(int code, String message){
		return new RestResp(code, message, null);
	}

	public static RestResp fail(String message) {
		return new RestResp(CODE_FAIL, message, null);
	}

	public static RestResp fail() {
		return new RestResp(CODE_FAIL, MSG_FAIL, null);
	}
}