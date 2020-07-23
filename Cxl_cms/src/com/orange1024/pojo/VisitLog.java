package com.orange1024.pojo;


/**
 * @author Mr_Cxl
 * @Title VisitLog
 * @data 2020年7月17日 下午2:42:51
 * @Describe:拜访记录
 */
public class VisitLog {

	private String visitTime; // 拜访时间
	private Integer userId; // 业务员ID
	private String realName; // 业务员名称
	private Integer custId; // 客户ID
	private String custName; // 客户名称
	private String visitDesc; // 拜访内容
	private String createTime; // 创建时间
	
	private Long  count;//数量
	public String getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getVisitDesc() {
		return visitDesc;
	}
	public void setVisitDesc(String visitDesc) {
		this.visitDesc = visitDesc;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	

}
