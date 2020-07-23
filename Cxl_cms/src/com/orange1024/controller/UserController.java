package com.orange1024.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.orange1024.common.CodeMsg;
import com.orange1024.common.Constant;
import com.orange1024.common.Result;
import com.orange1024.pojo.User;
import com.orange1024.service.IUserService;
import com.orange1024.service.impl.UserServiceImpl;
import com.orange1024.util.RespUtil;
import com.orange1024.util.SessionUtil;

/**
 * 
 * @author Mr_Cxl
 * @Title UserController
 * @data 2020年7月17日 上午10:28:36
 * @Describe:用户的控制类
 */
@WebServlet("/user.do")
@MultipartConfig //标记 用于标识 servlet 要去处理文件流数据
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 7723086583204133716L;
	
	IUserService userService = new UserServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String service = req.getParameter("service");
		if("login".equals(service)) {
			login(req,resp);
		}else if("loginOut".equals(service)) {
			loginOut(req,resp);
		}else if("page".equals(service)) {
			page(req,resp);
		}else if("resetPwd".equals(service)) {
			resetPwd(req,resp);
		}else if("delete".equals(service)) {
			delete(req,resp);
		}else if("add".equals(service)) {
			add(req,resp);
		}else if("getAllSalesman".equals(service)) {
			getAllSalesman(req,resp);
		}else if("updatePassword".equals(service)) {
			updatePassword(req,resp);
		}else if("upload".equals(service)) {
			upload(req,resp);
		}
		
	
	}
	
	/**
	 * 
	 * 2020年7月17日上午10:29:07
	 * @Describe 根据请求参数查询分页数据
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void  page(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
		String page = req.getParameter("page");// 页码
		String limit = req.getParameter("limit");// 每页数据长度
		//以上2个参数 肯定必传 而且默认参数  以下4个参数 是可选参数 可能有 可能没有
		String username = req.getParameter("username");
		String realName = req.getParameter("realName");
		String role = req.getParameter("role");
		String state = req.getParameter("state");
		//创建一个查询参数的容器
		Map<String,String> params = new HashMap<>();
		if(username != null && username !="") {
			params.put("username", username);
		}
		if(realName != null && realName !="") {
			params.put("realName", realName);
		}
		if(role != null && role !="") {
			params.put("role", role);
		}
		if(state != null && state !="") {
			params.put("state", state);
		}
		//根据参数获取数据
		Result rs = userService.queryPage(params,page,limit);
		//将数据输出
		RespUtil.write(resp, rs);
	}

	
	/**
	 * 
	 * 2020年7月17日上午10:29:23
	 * @Describe: 用户登录API
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		//进行登录操作
		Result rs = userService.login(username, password);
		//如果登录成功，将登录用户放入session中 用于记录当前用户
		if(rs.getCode().equals(CodeMsg.SUCCESS.code)) {
			//将用户保存到session
			SessionUtil.saveCurrentUser(req, rs.getData());
			//清楚data数据 避免将用户信息泄露给客户端
			rs.setData(null);
		}
		//将数据进行输出 JSON格式输出
		RespUtil.write(resp, rs);
	}
	
	
	/**
	 * 2020年7月17日上午10:29:43
	 * @Describe: 用户退出
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void loginOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//清除当前登录的用户
		SessionUtil.removeCurrentUser(req);
		//跳转到登录页面
		resp.sendRedirect("index.jsp");
	}
	
	
	/**
	 * 2020年7月17日上午10:30:23
	 * @Describe: 重置指定ID的用户密码
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void resetPwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");//获取ID
		Result rs = userService.resetPassword(id);
		RespUtil.write(resp, rs);
	}
	/**
	 * 2020年7月17日上午10:32:08
	 * @Describe: 员工离职
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");//获取ID
		Result rs = userService.delete(id);
		RespUtil.write(resp, rs);
	}
	
	
	/**
	 * 2020年7月17日上午10:32:24
	 * @Describe: 新增用户信息
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String realName = req.getParameter("realName");
		String role = req.getParameter("role");
		User user = new User();
		user.setUsername(username);
		user.setPassword(Constant.DEFAULT_PASSWORD);
		user.setRealName(realName);
		user.setRole(Integer.parseInt(role));
		user.setState(Constant.USER_STATE_VALID);
		Result rs = userService.add(user);
		RespUtil.write(resp, rs);
	}
	
	
	/**
	 * 2020年7月17日上午10:32:38
	 * @Describe: 获取所有的业务员 	
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void getAllSalesman(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result rs = userService.querySalesman();
		RespUtil.write(resp, rs);
	}
	
	
	/**
	 * 2020年7月17日上午10:33:07
	 * @Describe: 修改密码
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String password = req.getParameter("password");//原密码
		String confirmPassword = req.getParameter("confirmPassword");//新密码
		//校验原密码 是否正确
		String pwd = SessionUtil.getCurrentUser(req).getPassword();
		if(!pwd.equals(password)) {//原密码不正确 则直接返回
			RespUtil.write(resp, new Result(CodeMsg.USER_UPDATE_PASSWORD_ERROR));
			return;
		}
		Result rs = userService.updatePassword(SessionUtil.getCurrentUser(req).getId(),confirmPassword);
		//将用户进行移除
		SessionUtil.removeCurrentUser(req);
		
		RespUtil.write(resp, rs);
	}
	
	/**
	 * 
	 * 2020年7月17日上午10:33:34
	 * @Describe: 文件上传
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void upload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取上传的文件数据
		Part part = req.getPart("userImg");
		//获取数据的类型
		//part.getContentType()
		// 获取
		//part.getName() //获取 文件域的name属性值
		//part.getSize() //获取上传的文件大小
		//part.getInputStream() //获取上传的文件的输入流数据
		//part.write(fileName);//将上传的文件流数据 输出
		System.out.println(part.getContentType());
		System.out.println(part.getName());
		System.out.println(part.getSize());
		// 张三     1.jpg      imgs/1.jpg  
		// 李四     2.jpg      imgs/2.jpg
		// 王五     1.jpg      imgs/1.jpg
		// 文件的名称是没有必要使用的，因为可能出现文件重名  会发生文件覆盖
		//解决文件覆盖 ： 使用当前用户ID 作为文件名 + 文件格式
		String fileRealName = getFileRealName(part);
		String fileSuffix = fileRealName.substring(fileRealName.lastIndexOf("."));//获取文件的后缀
		String fileName = SessionUtil.getCurrentUser(req).getId() + fileSuffix;//新的文件名称
		String url = "imgs/"+fileName;//用于保存在数据库中 地址  用于图片的访问
		//fileName : 文件的保存的绝对路径  物理路径
		//获取 imgs 文件夹的物理路径  要获取项目发布后的imgs文件夹的物理路径
		String imgsPath = req.getServletContext().getRealPath("imgs");
		//File.separator 根据操作系统 使用相应的盘符路径分隔符
		String filePath = imgsPath +File.separator+fileName;//  \ 盘符路径 分割符  linux  /
		//保存文件
		part.write(filePath);
		
		Result rs = userService.updateImg(SessionUtil.getCurrentUser(req).getId(),url);
		RespUtil.write(resp, rs);
	}
	
	/**
	 * 
	 * 2020年7月17日上午10:33:47
	 * @Describe: 获取文件的原名称
	 * @param part
	 * @return
	 */
	private String getFileRealName(Part part) {
		String header = part.getHeader("Content-Disposition");
		//form-data; name="userImg"; filename="timer.jpg"
		String[]  file = header.split(";");
		String name = file[2];
		name = name.substring(name.indexOf("\"")+1,name.lastIndexOf("\""));
		return name;
	}
	

}
