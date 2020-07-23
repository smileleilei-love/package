package com.orange1024.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.orange1024.common.Result;
import com.orange1024.dao.VisitLogDao;
import com.orange1024.pojo.VisitLog;
import com.orange1024.service.IVisitLogService;
import com.orange1024.common.EchartsData;

public class VisitLogServiceImpl implements IVisitLogService {

	VisitLogDao visitLogDao = new VisitLogDao();
	
	@Override
	public Result add(VisitLog log) {
		visitLogDao.insert(log);
		return new Result();
	}
	@Override
	public Result pie(Map<String, String> params) {
		//所有的数据
		List<VisitLog> data = visitLogDao.queryPie(params);
		//图例数据 
		List<String> legendData = new ArrayList<>();
		//图表系列数据
		List<Map<String,Object>> seriesData = new ArrayList<Map<String,Object>>(); 
		for (VisitLog log : data) {
			String name = log.getRealName();//名字
			long value = log.getCount();//数量
			legendData.add(name);//将名字放入图例数据
			//图表系列数据中的每个项
			Map<String,Object> item = new HashMap<String, Object>();
			item.put("name", name);
			item.put("value", value);
			seriesData.add(item);
		}
		//echarts图表数据
		EchartsData echartsData = new EchartsData();
		echartsData.setLegendData(legendData);//设置图例数据
		echartsData.setSeriesData(seriesData);//设置图表系列数据
		return new Result(echartsData);
	}
}
