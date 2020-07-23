package com.orange1024.service;

import java.util.Map;

import com.orange1024.common.Result;
import com.orange1024.pojo.VisitLog;

/**
 * @author Mr_Cxl
 * @Title IVisitLogService
 * @data 2020年7月17日 下午2:46:59
 * @Describe: 拜访记录业务类
 */
public interface IVisitLogService {
	
	Result add(VisitLog log);
	Result pie(Map<String, String> params);
}
