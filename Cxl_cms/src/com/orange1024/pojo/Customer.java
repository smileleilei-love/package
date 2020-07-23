package com.orange1024.pojo;

/**
 * @author Mr_Cxl
 * @Title Customer
 * @data 2020年7月17日 下午2:42:25
 * @Describe: 客户实体类
 */
public class Customer {
	private Integer id; //客户ID
	private String custName; //客户名称
	private String company; //客户公司
	private String position; //职位
	private Integer salary; //工资
	private String phone; //电话
	private Integer sex; //性别  1 男  2女
	private Integer state; //状态 1 有效  2 无效
	private Integer userId;  //业务员ID
	private String createTime;  //创建时间
	private String modifyTime;  //修改时间
	
	private String realName; //业务员名称
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
