package com.orange1024.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.orange1024.common.Constant;
import com.orange1024.pojo.User;



/**
 * 
 * @author Mr_Cxl
 * @Title SessionUtil
 * @data 2020年7月17日 上午9:55:38
 * @Describe:session工具类  对session中用户进行操作
 */

public abstract class SessionUtil {
	
	/**
	 * 
	 * 2020年7月17日上午10:01:31
	 * @Describe:将当前用户放入session
	 * @param req
	 * @param data
	 */
	public static void saveCurrentUser(HttpServletRequest req,Object data) {
		HttpSession session = req.getSession();
		session.setAttribute(Constant.SESSION_USER_KEY, data);
	};
	/**
	 * 
	 * 2020年7月17日上午10:01:17
	 * @Describe:从session中获取当前用户
	 * @param req
	 * @return
	 */
	public static User getCurrentUser(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute(Constant.SESSION_USER_KEY);
		//判断obj 是不是 User类型
		if(obj instanceof User) {
			return (User)obj;
		}
		return null;
	};
	
	/**
	 * 
	 * 2020年7月17日上午10:00:54
	 * @Describe:从session删除当前用户
	 * @param req
	 */
	public static void removeCurrentUser(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.removeAttribute(Constant.SESSION_USER_KEY);
		
	};

}
