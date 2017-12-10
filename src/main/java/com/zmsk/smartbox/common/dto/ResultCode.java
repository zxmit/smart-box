package com.zmsk.smartbox.common.dto;

/***
 * 结果响应码
 * 
 * @author warrior
 *
 */
public class ResultCode {

	/** 成功 **/
	public static final int SUCCESS = 200;

	/** 失败 **/
	public static final int ERROR = 500;

	/** 参数错误 **/
	public static final int INVALIDPARAM = 400;

	/** 认证失败 **/
	public static final int UNAUTH = 401;

	/** 登入冲突code **/
	public static final int CONFLICT = 409;
}
