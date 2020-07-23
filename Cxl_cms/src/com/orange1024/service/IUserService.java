package com.orange1024.service;

import java.util.Map;

import com.orange1024.common.Result;
import com.orange1024.pojo.User;


/**
 * 
 * @author Mr_Cxl
 * @Title IUserService
 * @data 2020年7月17日 下午6:20:11
 * @Describe:用戶專業接口
 */
public interface IUserService {
	
	/**
	 * 
	 * @date 2020年7月17日下午6:20:53
	 * @Describe: 登录业务
	 * @param username
	 * @param password
	 * @return: Result
	 */
	Result login(String username,String password);
	
	/**
	 * 
	 * @date 2020年7月17日下午6:21:09
	 * @Describe: 分页查询数据
	 * @param params
	 * @param page
	 * @param limit
	 * @return: Result
	 */
	Result queryPage(Map<String, String> params, String page, String limit);
	/**
	 * 
	 * @date 2020年7月17日下午6:21:22
	 * @Describe: 重置密码
	 * @param id
	 * @return: Result
	 */
	Result resetPassword(String id);
	
	/**
	 * @date 2020年7月17日下午6:22:34
	 * @Describe: 员工离职
	 * @param id
	 * @return: Result
	 */
	Result delete(String id);
	/**
	 * @date 2020年7月17日下午6:22:19
	 * @Describe: 新增用户信息
	 * @param user
	 * @return: Result
	 */
	Result add(User user);
	
	/**
	 * @date 2020年7月17日下午6:22:04
	 * @Describe: 获取所有的在职的业务员
	 * @return: Result
	 */
	Result querySalesman();
	/**
	 * @date 2020年7月17日下午6:21:52
	 * @Describe: 修改指定的用户的密码
	 * @param id
	 * @param password
	 * @return: Result
	 */
	Result updatePassword(Integer id, String password);
	/**
	 * @date 2020年7月17日下午6:21:39
	 * @Describe: 修改用户头像
	 * @param id
	 * @param url
	 * @return: Result
	 */
	Result updateImg(Integer id, String url);
	/**
	 * 
	 * @date 2020年7月17日下午6:23:39
	 * @Describe：刪除用戶
	 * @param id
	 * @return: Result
	 */
	

}
