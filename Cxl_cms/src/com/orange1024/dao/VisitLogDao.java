package com.orange1024.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.orange1024.common.Constant;
import com.orange1024.pojo.VisitLog;

import cn.hutool.core.date.DateUtil;

/**
 * @author Mr_Cxl
 * @Title VisitLogDao
 * @data 2020年7月17日 下午2:39:07
 * @Describe:拜访记录操作类
 */
public class VisitLogDao extends BaseDao {
	
	
	
	/**
	 * @date 2020年7月17日下午2:39:25
	 * @Describe: 新增拜访记录
	 * @param log
	 */
	public void  insert(VisitLog log) {
		String sql = "insert  into visit_log value(?,?,?,?,?,?,?)";
		String createTime = DateUtil.format(new Date(), Constant.YYYY_MM_DD_HH_MM_SS);
		super.update(sql, log.getVisitTime(),log.getUserId(),log.getRealName(),log.getCustId(),log.getCustName(),log.getVisitDesc(),createTime);
	}
	
	public List<VisitLog> queryPie(Map<String, String> params) {
		String sql = "select real_name as realName ,count(1) as count from visit_log  where 1=1  ";//GROUP BY  user_id ,real_name
		if(params.containsKey("minTime")) {
			sql = sql + "  and  visit_time >='"+params.get("minTime")+"' ";
		}
		if(params.containsKey("maxTime")) {
			sql = sql + "  and  visit_time <='"+params.get("maxTime")+"' ";
		}
		sql = sql  + "GROUP BY  user_id ,real_name";
		return super.selectList(sql, VisitLog.class);
	}
	

}
