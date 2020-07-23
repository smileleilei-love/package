package com.orange1024.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.orange1024.common.Result;





/**
 * 
 * @author Mr_Cxl
 * @Title RespUtil
 * @data 2020年7月17日 上午9:50:06
 * @Describe：将数据以JOSN的格式进行返回
 */
public abstract class RespUtil {
	
	/**
	 *
	 * @param resp
	 * @param rs
	 * @throws IOException
	 */
	public static void write(HttpServletResponse resp,Result rs) throws IOException {
		resp.setCharacterEncoding("UTF-8");//设置编码
		resp.setContentType("text/json;charset=UTF-8");//设置响应数据的类型和编码
		PrintWriter writer = resp.getWriter();
		//将Result 对象转化为JSON字符串
		//使用阿里工具类  fastJson 直接进行转换
		String jsonString = JSON.toJSONString(rs);
		writer.print(jsonString);
		//清理缓存
		writer.flush();
		//释放资源
		writer.close();
	}

}
