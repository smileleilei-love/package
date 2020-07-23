package com.orange1024.common;
/**
 * 
 * @author Mr_Cxl
 * @Title Constant
 * @data 2020年7月17日 上午10:20:54
 * @Describe:全局常量信息接口
 */
public interface Constant {
	
	/**
	 * 	用户管理员
	 */
	Integer  USER_ROLE_ADMIN = 1;
	/**
	 * 	业务员
	 */
	Integer  USER_ROLE_SALES = 2;
	/**
	 *	用户状态  1 在职
	 */
	Integer  USER_STATE_VALID = 1;
	/**
	 *	用户状态  2 离职
	 */
	Integer  USER_STATE_INVALID = 2;
	
	/**
	 *	客户性别  男  
	 */
	Integer CUSTOMER_SEX_M = 1;
	/**
	 *	客户性别  女
	 */
	Integer CUSTOMER_SEX_W = 2;
	/**
	 * 	存入session的用户的key
	 */
	String SESSION_USER_KEY = "user";
	/**
	 *	默认密码	
	 */
	String DEFAULT_PASSWORD = "123456";
	/**
	 *	时间格式:yyyy-MM-dd HH:mm:ss
	 */
	String  YYYY_MM_DD_HH_MM_SS =  "yyyy-MM-dd HH:mm:ss";
	/**
	 * 	客户状态： 1 有效  
	 */
	Integer CUSTOMER_STATE_VALID = 1;
	/**
	 * 	客户状态： 2 无效效  
	 */
	Integer CUSTOMER_STATE_INVALID = 2;

}
