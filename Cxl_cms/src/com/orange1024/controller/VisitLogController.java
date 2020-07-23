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

import com.orange1024.common.Result;
import com.orange1024.pojo.VisitLog;
import com.orange1024.service.IVisitLogService;
import com.orange1024.service.impl.VisitLogServiceImpl;
import com.orange1024.util.RespUtil;
import com.orange1024.util.SessionUtil;


@WebServlet("/visit.do")
public class VisitLogController extends HttpServlet {

	private static final long serialVersionUID = -5731249789139513724L;
	
	IVisitLogService  visitLogService = new VisitLogServiceImpl();
	
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
	 * 2020年7月17日上午10:34:34
	 * @Describe: 新增拜访记录
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String custId = req.getParameter("custId");//客户ID
		String custName = req.getParameter("custName");//客户名称
		String visitTime = req.getParameter("visitTime");//拜访时间
		String visitDesc = req.getParameter("visitDesc");//拜访内容
		VisitLog log = new VisitLog();
		log.setVisitTime(visitTime);
		log.setCustId(Integer.parseInt(custId));
		log.setCustName(custName);
		log.setVisitDesc(visitDesc);
		log.setUserId(SessionUtil.getCurrentUser(req).getId());
		log.setRealName(SessionUtil.getCurrentUser(req).getRealName());
		//添加
		Result rs = visitLogService.add(log);
		//返回数据
		RespUtil.write(resp, rs);
	}
	
	protected void pie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String minTime = req.getParameter("minTime");//最小拜访时间
		String maxTime = req.getParameter("maxTime");//最大拜访时间
		Map<String,String>  params = new  HashMap<>();
		if(minTime != null && minTime !="") {
			params.put("minTime", minTime);
		}
		if(maxTime != null && maxTime !="") {
			params.put("maxTime", maxTime);
		}
		//添加
		Result rs = visitLogService.pie(params);
		//返回数据
		RespUtil.write(resp, rs);
	}


}
