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


/**
 * @author Mr_Cxl
 * @Title CharsetEncodingFilter
 * @data 2020年7月17日 下午2:39:55
 * @Describe: 编码过滤器
 */
@WebFilter(urlPatterns = "*.do" )//需要过滤的url的规则
public class CharsetEncodingFilter  implements Filter{
	//当filter创建时调用  可以进行配置初始化
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	/**
	 *  进行一个具体的过滤
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		//在请求进行具体的处理前 进行前置的处理
		//放行  去调用具体的servlet中 service方法
		chain.doFilter(req, resp);
	}
	//当filter对象销毁时调用
	@Override
	public void destroy() {
		
	}

}
