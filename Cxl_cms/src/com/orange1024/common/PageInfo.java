package com.orange1024.common;
import java.util.List;
/**
 * 
 * @author Mr_Cxl
 * @Title PageInfo
 * @data 2020年7月17日 上午10:21:18
 * @Describe 分页数据信息
 * @param <T>
 */
public class PageInfo<T> {
	
	private long count;//总数据条数
	
	
	private List<T> data; //具体的数据


	public long getCount() {
		return count;
	}


	public void setCount(long count) {
		this.count = count;
	}


	public List<T> getData() {
		return data;
	}


	public void setData(List<T> data) {
		this.data = data;
	}
	
	
	

}
