package com.orange1024.pojo;

/**
 * @author Mr_Cxl
 * @Title User
 * @data 2020年7月17日 下午2:42:39
 * @Describe:用户表实体类
 */
public class User {

	private Integer id;//用户ID
	private String username; //用户登录名
	private String password; //用户登录密码
	private String realName;// 真实姓名
	private Integer role; //用户角色 1  管理员  2 业务员
	private Integer state; //用户状态 1 在职  2离职
	private String img; //用户头像
	private String createTime; //创建时间
	private String modifyTime; //修改时间
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	
	

}
