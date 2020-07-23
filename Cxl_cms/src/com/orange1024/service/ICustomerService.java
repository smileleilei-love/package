package com.orange1024.service;

import java.util.Map;

import com.orange1024.common.Result;
import com.orange1024.pojo.Customer;



public interface ICustomerService {
	/**
	 * @date 2020年7月17日下午2:47:32
	 * @Describe: 查询客户的分页数据
	 * @param params
	 * @param page
	 * @param limit
	 * @return Result
	 */
	Result queryPage(Map<String, String> params, String page, String limit);
	/**
	 * @Title: add
	 * @author: Mr.T   
	 * @date: 2020年7月15日 上午9:43:00 
	 * @Description: TODO
	 * @param customer
	 * @return
	 * @return: Result
	 */
	
	/**
	 * @date 2020年7月17日下午2:47:48
	 * @Describe:增加
	 * @param customer
	 * @return Result
	 */
	Result add(Customer customer);
	/**
	 * @date 2020年7月17日下午2:50:20
	 * @Describe: 修改客户信息
	 * @param customer
	 * @return Result
	 */
	Result update(Customer customer);
	/**
	 * @date 2020年7月17日下午2:50:39
	 * @Describe: 根据ID 设置数据状态为失效
	 * @param id
	 * @return: Result
	 */
	Result delete(String... id);
	/**
	 * @date 2020年7月17日下午2:51:39
	 * @Describe: 批量修改业务员
	 * @param userId
	 * @param id
	 * @return: Result
	 */
	Result updateSalesman(String userId, String... id);
}
