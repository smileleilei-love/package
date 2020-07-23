package com.orange1024.common;
/**
 * 
 * @author Mr_Cxl
 * @Title CodeMsg
 * @data 2020年7月17日 上午10:19:59
 * @Describe: 业务码和业务消息枚举,存放所有要用到的业务码和对应的业务消息
 */
public enum CodeMsg {
	
	
	SUCCESS(200,"操作成功"),
	//错误的业务码 ：4开头 000 表示模块 000 表示该模块下的某个异常信息
	//一个项目可能N个模块  99个模块 411  第一个异常   4199199
	//4001001  表示第一个模块的第一个异常
	//4999999 表示第999个模块 999个异常
	USER_LOGIN_ERROR(4001001,"用户名或者密码错误"),
	USER_ACCOUNT_INVALID_ERROR(4001002,"用户账号已失效"),
	USER_DELETE_ERROR(4001003,"离职失败，业务员存在关联客户"),
	USER_ACCOUNT_EXIST_ERROR(4001004,"添加失败，账号已存在"),
	USER_UPDATE_PASSWORD_ERROR(4001005,"修改失败，密码不正确"),
	
	CUSTOMER_PHONE_USEED_ERROR(4002001,"添加失败，手机号已被使用"),
	CUSTOMER_UPDATE_ERROR(4002002,"修改失败，手机号已被使用"),
	;
	
	public Integer code;
	
	public String msg;

	private CodeMsg(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	
	

}
