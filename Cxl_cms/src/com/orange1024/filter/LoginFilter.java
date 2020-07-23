package com.orange1024.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orange1024.pojo.User;
import com.orange1024.util.SessionUtil;

/**
 * @author Mr_Cxl
 * @Title LoginFilter
 * @data 2020年7月17日 下午2:41:14
 * @Describe:登录拦截器    如果用户已经登录了，那么用户可以任意的请求服务器资源
 * 	如果用户没有登录，则访问需要登录的资源时，要先登录。
 *	需要登录才能访问的资源:
 *		服务器controller模块    一些jsp页面     大概
 *
 *	不需要登录就能访问的资源:
 *		登录页面、登录请求 、静态资源文件(CSS、JS、字体、图片等等)
 * 	如果用户没有登录，访问不是不拦截的内容，让用户跳转到 登录页面
 */
@WebFilter(urlPatterns = {"*.do","*.jsp"})//只拦截 以.do和.jsp结尾的后缀url请求
public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		//获取session中的用户
		User user = SessionUtil.getCurrentUser(req);
		//如果用户不为null   则说明已经登录  直接放行
		if(user != null) {
			chain.doFilter(req, resp);//放行
			return;
		}
		//没有登录的情况 :   index.jsp 、 user.do && service= login 
		//获取请求的资源路径
		String uri = req.getRequestURI();
		//获取业务标识
		String service = req.getParameter("service");
		if(uri.endsWith("redirect.jsp")|| uri.endsWith("index.jsp") || (uri.endsWith("user.do") && "login".equals(service))) {
			chain.doFilter(req, resp);//放行
			return;
		}
		//其他情况则跳转到 登录页面
		resp.sendRedirect(req.getContextPath()+"/redirect.jsp");
	}

	@Override
	public void destroy() {
		
	}

}
