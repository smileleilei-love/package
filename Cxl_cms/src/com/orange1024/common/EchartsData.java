package com.orange1024.common;

import java.util.List;

/**
 * 
 * @author Mr_Cxl
 * @Title EchartsData
 * @data 2020年7月18日 上午10:24:04
 * @Describe:执行的图像
 */
public class EchartsData {
	
	private List<String> legendData;//图例数据
	
	private Object  seriesData;//图表系列数据
	
	private List<String> xaxisData;//x轴的值
	
	private List<Object> yaxisData;//y轴的值
	

	public List<String> getLegendData() {
		return legendData;
	}

	public void setLegendData(List<String> legendData) {
		this.legendData = legendData;
	}

	public Object getSeriesData() {
		return seriesData;
	}

	public void setSeriesData(Object seriesData) {
		this.seriesData = seriesData;
	}

	public List<String> getXaxisData() {
		return xaxisData;
	}

	public void setXaxisData(List<String> xaxisData) {
		this.xaxisData = xaxisData;
	}

	public List<Object> getYaxisData() {
		return yaxisData;
	}

	public void setYaxisData(List<Object> yaxisData) {
		this.yaxisData = yaxisData;
	}

}
