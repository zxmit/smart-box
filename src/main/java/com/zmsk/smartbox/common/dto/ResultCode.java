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

	/** 请求超时 **/
	public static final int REQUESTTIMEOUT = 408;

	/** 登入冲突code **/
	public static final int CONFLICT = 409;

	/** 用户未注册 **/
	public static final int UNREGISTER = 601;

	/** 用户被拉入黑名单 **/
	public static final int INBLACKLIST = 602;

	/** 账单未完成 **/
	public static final int UNFINISHEDBILL = 603;

	/** 该类型无可用的格子 **/
	public static final int UNAVAIALABLECELL = 604;

	/** 开箱失败 **/
	public static final int OPENBOXFAIL = 605;

	/** 仓门未关闭 **/
	public static final int CELLUNCLOSED = 606;




}
