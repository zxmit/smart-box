package com.zmsk.smartbox.common.dto;

import java.io.Serializable;

/****
 * 自定义接口调用响应数据
 * 
 * @author warrior
 *
 */
public class ResultServiceDTO implements Serializable {

	private static final long serialVersionUID = -1715319878577518588L;

	/*** 状态码 **/
	private int code;

	/** 响应消息 **/
	private String msg;

	/*** 响应数据 **/
	private Object data;

	public ResultServiceDTO(int code, String msg) {
		this(code, msg, null);
	}

	public ResultServiceDTO(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public static ResultServiceDTO success() {
		return success(null);
	}

	public static ResultServiceDTO success(Object data) {
		return new ResultServiceDTO(ResultCode.SUCCESS, "success", data);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResultServiceDTO [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
}
