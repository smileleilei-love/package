package com.orange1024.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.orange1024.common.Constant;
import com.orange1024.common.PageInfo;
import com.orange1024.pojo.User;

import cn.hutool.core.date.DateUtil;

/**
 * @author Mr_Cxl
 * @Title UserDao
 * @data 2020年7月17日 下午2:33:25
 * @Describe: 用户数据操作类
 */
public class UserDao extends BaseDao {
	/**
	 * 2020年7月17日下午2:33:42
	 * @Describe: 根据用户名和密码查询用户
	 * @param username
	 * @param password
	 * @return User
	 */
	public User selectUser(String username, String password) {
		String sql = "select id,username,password,real_name as realName,role,state,img from user where username=? and password=?";
		return super.selectOne(sql, User.class, username,password);
	}
	
	/**
	 * 2020年7月17日下午2:34:18
	 * @Describe: 根据条件分页查询数据
	 * @param params
	 * @param page
	 * @param limit
	 * @return PageInfo<User>
	 */
	public PageInfo<User> selectPage(Map<String, String> params, String page, String limit) {
		//查询列表数据 
		//查询符合条件数据条数
		//直接返回 list 可以吗？ 需要 count  和  list 没有 count   直接返回count 就没有 list
		//只能将 count 和 list包装在一起 ：PageInfo  里面 就有 count 和  list
		String sql = "select id,username,password,real_name as realName,role,state,create_time as createTime from user  where 1=1 ";
		//根据params 生成不一样的sql语句
		if(params.containsKey("username")) {//如果 查询参数里面有 username 则 
			sql = sql + "  and username ='"+params.get("username")+"' ";
		}
		if(params.containsKey("realName")) {//如果 查询参数里面有 realName 则 
			sql = sql + "  and real_name like '%"+params.get("realName")+"%' ";
		}
		if(params.containsKey("role")) {//如果 查询参数里面有 role 则 
			sql = sql + "  and role ="+params.get("role")+" ";
		}
		if(params.containsKey("state")) {//如果 查询参数里面有 state 则 
			sql = sql + "  and state ="+params.get("state")+" ";
		}
		return super.selectPage(sql, User.class, page, limit);
	}
	/**
	 * 2020年7月17日下午2:36:15
	 * @Describe: 根据ID 修改密码
	 * @param id
	 * @param password
	 */
	public void updatePassword(String id, String password) {
		String sql = "update user set password = ? where id = ?";
		super.update(sql,password,id);
	}
	
	/**
	 * 2020年7月17日下午2:36:39
	 * @Describe: 修改员工状态
	 * @param id
	 * @param state
	 */
	public void updateState(String id, Integer state) {
		String sql = "update user set state = ? where id = ?";
		super.update(sql,state,id);
	}
	
	/**
	 * @date:2020年7月17日下午2:37:36
	 * @Describe: 根据登录名查询用户
	 * @param username
	 * @return User
	 */
	
	public User selectUserByName(String username) {
		String sql = "select id,username,password,real_name as realName,role,state,create_time as createTime from user  where username = ? ";
		return super.selectOne(sql, User.class,username);
	}
	/**
	 * @date 2020年7月17日下午2:38:19
	 * @Describe: 新增用户信息
	 * @param user
	 */
	public void insertUser(User user) {
		String sql = "insert into user value(0,?,?,?,?,?,'',?,?)";
		String createTime = DateUtil.format(new Date(),Constant.YYYY_MM_DD_HH_MM_SS);
		String modifyTime = createTime;
		super.update(sql, user.getUsername(),user.getPassword(),user.getRealName(),user.getRole(),user.getState(),createTime,modifyTime);
	}
	/**
	 * @date 2020年7月17日下午2:38:31
	 * @Describe: 根据角色和状态查询用户信息
	 * @param role
	 * @param state
	 * @return List<User>
	 */
	public List<User> selectListByRoleAndState(Integer role, Integer state) {
		String sql = "select id,real_name as realName from user  where role = ? and state = ? ";
		return super.selectList(sql, User.class, role,state);
	}
	/**
	 * @date 2020年7月17日下午2:38:45
	 * @Describe : 修改用户头像
	 * @param id
	 * @param url
	 */
	public void updateImg(String id, String url) {
		String sql = "update user set img = ? where id = ?";
		super.update(sql,url,id);
	}
	
	
}
