package com.orange1024.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orange1024.common.Constant;
import com.orange1024.common.Result;
import com.orange1024.pojo.Customer;
import com.orange1024.service.ICustomerService;
import com.orange1024.service.IUserService;
import com.orange1024.service.impl.CustomerServiceImpl;
import com.orange1024.service.impl.UserServiceImpl;
import com.orange1024.util.RespUtil;
import com.orange1024.util.SessionUtil;


/**
 * 
 * @author Mr_Cxl
 * @Title CustomerController
 * @data 2020年7月17日 上午10:23:11
 * @Describe:客户控制类
 */
@WebServlet("/customer.do")
public class CustomerController extends HttpServlet {

	private static final long serialVersionUID = -3296890050381719116L;

	ICustomerService customerService = new CustomerServiceImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String service = req.getParameter("service");
		//service 的值和方法名相同 --- > 使用service 当做方法名  获取指定的方法
		try {
			Method method = this.getClass().getDeclaredMethod(service, HttpServletRequest.class,HttpServletResponse.class);
			//调用方法
			method.invoke(this, req,resp);
			//以上两行代码 代替if else 
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 2020年7月17日上午10:25:27
	 * @Describe:获取分页的客户数据
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		//可选的查询参数
		String userId = req.getParameter("userId");
		String custName = req.getParameter("custName");
		String company = req.getParameter("company");
		String sex = req.getParameter("sex");
		String position = req.getParameter("position");
		String minCreateTime = req.getParameter("minCreateTime");
		String maxCreateTime = req.getParameter("maxCreateTime");
		String state = req.getParameter("state");
		//查询参数容器
		Map<String,String> params = new HashMap<>();
		if(userId != null && userId !="") {
			params.put("userId", userId);
		}
		if(custName != null && custName !="") {
			params.put("custName", custName);
		}
		if(company != null && company !="") {
			params.put("company", company);
		}
		if(sex != null && sex !="") {
			params.put("sex", sex);
		}
		if(position != null && position !="") {
			params.put("position", position);
		}
		if(minCreateTime != null && minCreateTime !="") {
			params.put("minCreateTime", minCreateTime);
		}
		if(maxCreateTime != null && maxCreateTime !="") {
			params.put("maxCreateTime", maxCreateTime);
		}
		if(state != null && state !="") {
			params.put("state", state);
		}
		Result rs = customerService.queryPage(params, page, limit);
		RespUtil.write(resp, rs);
	}
	/**
	 * 
	 * 2020年7月17日上午10:25:47
	 * @Describe 新增接口
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String custName = req.getParameter("custName");
		String company = req.getParameter("company");
		String position = req.getParameter("position");
		String sex = req.getParameter("sex");
		String salary = req.getParameter("salary");
		String phone = req.getParameter("phone");
		//赋值
		Customer customer = new Customer();
		customer.setCustName(custName);
		customer.setCompany(company);
		customer.setPosition(position);
		customer.setSalary(Integer.parseInt(salary));
		customer.setSex(Integer.parseInt(sex));
		customer.setPhone(phone);
		customer.setUserId(SessionUtil.getCurrentUser(req).getId());
		customer.setState(Constant.CUSTOMER_STATE_VALID);
		Result rs = customerService.add(customer);
		RespUtil.write(resp, rs);
	}
	
	/**
	 * 
	 * 2020年7月17日上午10:26:30
	 * @Describe: 修改数据
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String custName = req.getParameter("custName");
		String company = req.getParameter("company");
		String position = req.getParameter("position");
		String sex = req.getParameter("sex");
		String salary = req.getParameter("salary");
		String phone = req.getParameter("phone");
		//赋值
		Customer customer = new Customer();
		customer.setId(Integer.parseInt(id));
		customer.setCustName(custName);
		customer.setCompany(company);
		customer.setPosition(position);
		customer.setSalary(Integer.parseInt(salary));
		customer.setSex(Integer.parseInt(sex));
		customer.setPhone(phone);
		Result rs = customerService.update(customer);
		RespUtil.write(resp, rs);
	}
	
	/**
	 * 
	 * 2020年7月17日上午10:27:18
	 * @Describe: 删除数据
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deletes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] id = req.getParameterValues("id");
		Result rs = customerService.delete(id);
		RespUtil.write(resp, rs);
	}
	
	/**
	 * 
	 * @date 2020年7月18日下午6:16:15
	 * @Describe:删除客户
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");//获取ID
		Result rs =customerService.delete(id);
		RespUtil.write(resp, rs);
	}
	/**
	 * 
	 * 2020年7月17日上午10:27:02
	 * @Describe: 批量修改客户的业务员
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void setSalesman(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] id = req.getParameterValues("id");
		String userId = req.getParameter("userId");
		Result rs = customerService.updateSalesman(userId,id);
		RespUtil.write(resp, rs);
	}
}
