package com.orange1024.service.impl;

import java.util.List;
import java.util.Map;

import com.orange1024.common.CodeMsg;
import com.orange1024.common.Constant;
import com.orange1024.common.PageInfo;
import com.orange1024.common.Result;
import com.orange1024.dao.CustomerDao;
import com.orange1024.dao.UserDao;
import com.orange1024.pojo.Customer;
import com.orange1024.pojo.User;
import com.orange1024.service.IUserService;


public class UserServiceImpl implements IUserService {
	
	UserDao dao = new UserDao();
	CustomerDao customerDao = new CustomerDao();

	@Override
	public Result login(String username, String password) {
		//根据用户名和密码查询用户
		User user = dao.selectUser(username, password);
		if(user == null) { //用户名或者密码错误
			return new Result(CodeMsg.USER_LOGIN_ERROR);
		}
		//判断用户状态  在职 还是离职  离职是不能登录
		if(user.getState().equals(Constant.USER_STATE_INVALID)) {
			return new Result(CodeMsg.USER_ACCOUNT_INVALID_ERROR);
		}
		return new Result(user);
	}

	@Override
	public Result queryPage(Map<String, String> params, String page, String limit) {
		//分页数据
		PageInfo<User> pageInfo = dao.selectPage(params,page,limit);
		return new Result(pageInfo);
	}

	@Override
	public Result resetPassword(String id) {
		dao.updatePassword(id,Constant.DEFAULT_PASSWORD);
		return new Result();
	}

	@Override
	public Result delete(String id) {
		//根据员工ID 查询客户  如果存在客户 则提示该员工还有关联客户  如果没有则修改状态 办理离职
		List<Customer> list = customerDao.selectListByUserId(id);
		if(list != null && !list.isEmpty()) {//业务员存在关联客户
			return new Result(CodeMsg.USER_DELETE_ERROR);
		}
		dao.updateState(id,Constant.USER_STATE_INVALID);
		dao.updateState(id,Constant.CUSTOMER_STATE_INVALID);
		return new Result();
	}

	
	@Override
	public Result add(User user) {
		//业务数据校验  根据登录名查询数据
		User u = dao.selectUserByName(user.getUsername());
		if(u != null) {//登录名已经被使用了
			return new Result(CodeMsg.USER_ACCOUNT_EXIST_ERROR);
		}
		dao.insertUser(user);
		return new Result();
	}

	@Override
	public Result querySalesman() {
		List<User> users = dao.selectListByRoleAndState(Constant.USER_ROLE_SALES,Constant.USER_STATE_VALID);
		return new Result(users);
	}

	@Override
	public Result updatePassword(Integer id, String password) {
		dao.updatePassword(String.valueOf(id), password);
		return new Result();
	}

	@Override
	public Result updateImg(Integer id, String url) {
		dao.updateImg(String.valueOf(id), url);
		return new Result();
	}

	

}
