package com.orange1024.service.impl;

import java.util.Map;

import com.orange1024.common.CodeMsg;
import com.orange1024.common.Constant;
import com.orange1024.common.PageInfo;
import com.orange1024.common.Result;
import com.orange1024.dao.CustomerDao;
import com.orange1024.pojo.Customer;
import com.orange1024.service.ICustomerService;



public class CustomerServiceImpl implements ICustomerService {
	CustomerDao customerDao = new CustomerDao();

	@Override
	public Result queryPage(Map<String, String> params, String page, String limit) {
		PageInfo<Customer> pageInfo = customerDao.selectPage(params,page, limit);
		return new Result(pageInfo);
	}

	@Override
	public Result add(Customer customer) {
		//做业务数据校验  手机号不能重复
		//根据手机号查询客户
		Customer c = customerDao.selectOneByPhone(customer.getPhone());
		if(c != null) {
			return new Result(CodeMsg.CUSTOMER_PHONE_USEED_ERROR);
		}
		customerDao.insert(customer);
		return new Result();
	}

	@Override
	public Result update(Customer customer) {
		//做业务数据校验  手机号不能重复
		//根据手机号查询客户
		Customer c = customerDao.selectOneByPhone(customer.getPhone());
		//用户可能修改手机号  也可能不修改手机号 如果不修改手机号 手机号就是原号
		//根据原号 一定能够查出1条数据所以就不能只是判断数据是否存在 还要判断数据ID 和当前ID 是否一致
		//不一致 说明手机号不是原号 已经被其他人使用了
		if(c != null && !c.getId().equals(customer.getId())) { //
			return new Result(CodeMsg.CUSTOMER_UPDATE_ERROR);
		}
		customerDao.update(customer);
		return new Result();
	}
	
	@Override
	public Result delete(String... id) {
		customerDao.updateState(Constant.CUSTOMER_STATE_INVALID,id);
		return new Result();
	}

	@Override
	public Result updateSalesman(String userId, String... id) {
		customerDao.updateUserId(userId,id);
		return new Result();
	}

}
