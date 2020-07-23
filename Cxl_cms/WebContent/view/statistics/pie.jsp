<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>绘制饼状图</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/layui/css/layui.css"/>
</head>
<body>
	<form class="layui-form layui-form-pane">
		<div class="layui-form-item">
			<label class="layui-form-label">时间:</label>
			<div class="layui-input-inline" style="width: 160px;">
				<input type="text" readonly="readonly" id="minTime" placeholder="最小时间" autocomplete="off" class="layui-input">
			</div>
			<div class="layui-form-mid">-</div>
			<div class="layui-input-inline" style="width: 160px;">
				<input type="text" readonly="readonly" id="maxTime" placeholder="最大时间" autocomplete="off" class="layui-input">
			</div>
			
			<button class="layui-btn" id="searchBtn" type="button">查询</button>
			<button class="layui-btn layui-btn-primary" type="reset">清空</button>
		</div>
	</form>
	<div id="content" style="width: 600px;height: 600px"></div>


<!-- 1.引入 js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/echarts.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script type="text/javascript">
	layui.use(['jquery','laydate','layer'],function(){
		var $ = layui.jquery;
		var laydate = layui.laydate;
		var layer = layui.layer;
		var echart = echarts.init(document.getElementById("content")); 
		//渲染时间组件
		laydate.render({elem:"#minTime"});
		laydate.render({elem:"#maxTime"});
		//渲染饼状图图表参数

	var opt = {
			title : {
				text : '业务员拜访占比',
			},
			tooltip : {
				trigger : 'item',
				formatter : '{a} <br/>{b}:{c} ({d}%)'
			},
			legend : {//图例值
				
				
				data : null
			},
			series : {
				name : '拜访占比',
				type : 'pie',
				radius : '55%',
				center : [ '50%', '60%' ],
				data : null
			}
		};

		//点击按钮时  应该通过ajax 请求数据
		$("#searchBtn").click(function() {
			var minTime = $("#minTime").val();
			var maxTime = $("#maxTime").val();
			$.get("${pageContext.request.contextPath}/visit.do?service=pie", {minTime : minTime,maxTime : maxTime}, function(rs) {
				//获取到了数据
				if (rs.code != 200) {
					layer.msg(rs.msg);
					return false;
				}
				var data = rs.data;
				//将数据配置到渲染图表的对象中
				opt.legend.data = data.legendData;//图例数据赋值
				opt.series.data = data.seriesData;//图表系列数据赋值
				echart.setOption(opt);//根据参数进行渲染
			});

		}).click();
	

	})
</script>
</body>
</html>