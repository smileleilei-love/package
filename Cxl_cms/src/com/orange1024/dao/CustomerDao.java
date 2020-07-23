package com.orange1024.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.orange1024.common.Constant;
import com.orange1024.common.PageInfo;
import com.orange1024.pojo.Customer;

import cn.hutool.core.date.DateUtil;

public class CustomerDao extends BaseDao {
	
	/**
	 * 2020年7月17日下午2:30:29
	 * @Describe: 根据业务员ID 查询客户
	 * @param userId
	 * @return List<Customer>
	 */
	public List<Customer> selectListByUserId(String userId){
		String sql = "select id from customer where user_id = ?";
		return super.selectList(sql, Customer.class, userId);
	}
	/**
	 * 2020年7月17日下午2:30:48
	 * @Describe: 查询客户信息列表
	 * @param params
	 * @param page
	 * @param limit
	 * @return: PageInfo<Customer>
	 */
	public PageInfo<Customer> selectPage(Map<String, String> params, String page, String limit) {
		String sql = "select c.id,c.cust_name as custName,c.company,c.position,c.salary,c.phone,c.sex,c.state,c.user_id as userId,c.create_time as createTime ,u.real_name as realName from customer  c left join user u on c.user_id = u.id  where 1=1";
		if(params.containsKey("userId")) {//如果 查询参数里面有 userId 则 
			sql = sql + "  and c.user_id ='"+params.get("userId")+"' ";
		}
		if(params.containsKey("custName")) {//如果 查询参数里面有 custName 则 
			sql = sql + "  and c.cust_name like'%"+params.get("custName")+"%' ";
		}
		if(params.containsKey("company")) {//如果 查询参数里面有 company 则 
			sql = sql + "  and c.company like'%"+params.get("company")+"%' ";
		}
		if(params.containsKey("sex")) {//如果 查询参数里面有 sex 则 
			sql = sql + "  and c.sex ="+params.get("sex")+" ";
		}
		if(params.containsKey("position")) {//如果 查询参数里面有 position 则 
			sql = sql + "  and c.position like '%"+params.get("position")+"%' ";
		}
		if(params.containsKey("minCreateTime")) {//如果 查询参数里面有 minCreateTime 则 
			sql = sql + "  and c.create_time >= '"+params.get("minCreateTime")+" 00:00:00' ";
		}
		if(params.containsKey("maxCreateTime")) {//如果 查询参数里面有 maxCreateTime 则 
			sql = sql + "  and c.create_time <= '"+params.get("maxCreateTime")+" 23:59:59' ";
		}
		if(params.containsKey("state")) {//如果 查询参数里面有 state 则 
			sql = sql + "  and c.state ="+params.get("state")+" ";
		}
		sql = sql + " order by c.create_time";
		return super.selectPage(sql, Customer.class, page, limit);
	}
	/**
	 * 2020年7月17日下午2:31:14
	 * @Describe: 新增客户
	 * @param c 客户对象
	 */
	public void insert(Customer c) {
		String sql = "insert into customer value(0,?,?,?,?,?,?,?,?,?,?)";
		String createTime = DateUtil.format(new Date(), Constant.YYYY_MM_DD_HH_MM_SS);
		String modifyTime = createTime;
		super.update(sql, c.getCustName(),c.getCompany(),c.getPosition(),c.getSalary(),c.getPhone(),c.getSex(),c.getState(),c.getUserId(),createTime,modifyTime);
	}
	
	/**
	 * 2020年7月17日下午2:31:44
	 * @Describe: 根据手机号查询客户
	 * @param phone
	 * @return Customer
	 */
	public Customer selectOneByPhone(String phone) {
		String sql = "select id from customer where phone = ?";
		return super.selectOne(sql, Customer.class, phone);
	}
	
	/**
	 * 2020年7月17日下午2:32:08
	 * @Describe: 修改客户信息
	 * @param c
	 */
	public void update(Customer c) {
		String sql = "update customer set `cust_name` = ?, `company` = ?, `position` = ?, `salary` = ?, `phone` = ?, `sex` = ?, `modify_time` = ? WHERE `id` = ?";
		String modifyTime = DateUtil.format(new Date(), Constant.YYYY_MM_DD_HH_MM_SS);
		super.update(sql, c.getCustName(),c.getCompany(),c.getPosition(),c.getSalary(),c.getPhone(),c.getSex(),modifyTime,c.getId());
	}
	
	/**
	 * 2020年7月17日下午2:32:20
	 * @Describe: 修改客户状态
	 * @param state
	 * @param id
	 */
	public void updateState(Integer state, String... id) {
		String sql = "update customer set  state = ?,modify_time = ? WHERE `id` in(";
		for (String string : id) {
			sql = sql + string +",";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql = sql + ")";
		String modifyTime = DateUtil.format(new Date(), Constant.YYYY_MM_DD_HH_MM_SS);
		super.update(sql, state,modifyTime);
	}
	/**
	 * 2020年7月17日下午2:32:34
	 * @Describe: 修改客户的业务员ID
	 * @param userId
	 * @param id
	 */
	public void updateUserId(String userId, String... id) {
		String sql = "update customer set  user_id = ?,modify_time = ? WHERE `id` in(";
		for (String string : id) {
			sql = sql + string +",";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql = sql + ")";
		String modifyTime = DateUtil.format(new Date(), Constant.YYYY_MM_DD_HH_MM_SS);
		super.update(sql, userId,modifyTime);
	};

}
