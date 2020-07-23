package com.orange1024.common;


/**
 * 
 * @author Mr_Cxl
 * @Title Result
 * @data 2020年7月17日 上午10:22:01
 * @Describe:所有返回的业务数据封装类
 */
public class Result {
	
	private Integer code;//业务编码  默认 200  正常是200  其它值不正常
	
	private String msg;//业务消息  默认 操作成功，如果业务码不是200时，msg就是异常原因 
	
	private Object data;//业务数据  只有业务码正常时 附带的数据
	
	
	public Result() {
		this.code = CodeMsg.SUCCESS.code;
		this.msg = CodeMsg.SUCCESS.msg;
	}
	
	public Result(CodeMsg codeMsg) {
		this.code = codeMsg.code;
		this.msg = codeMsg.msg;
	}
	public Result(Object data) {
		this();//调用无参构造方法
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
