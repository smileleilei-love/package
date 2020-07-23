<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>echarts案例</title>
</head>
<body>

	<!--  2. 创建一个具备宽高的dom容器 -->
	<div id="content" style="width: 500px;height: 500px"></div>

<!-- 1.引入echarts JS -->
<script type="text/javascript" src="resources/echarts.min.js"></script>
<script type="text/javascript">
	//3. 初始化echarts 对象  绑定dom 绘制图表时 在对应dom中绘制
	var echart = echarts.init(document.getElementById("content"));
	//4. 准备需要绘制的图标的参数 (配置项)
	var opt = {//绘制图表的配置参数
			title: {
                text: 'ECharts 入门示例',
                textStyle:{//主标题样式
                	color:'red'
                },
                subtext:"副标题",
                subtextStyle:{//副标题的样式
                	color:'green',
                	fontWeight:'bold'
                }
            },
            tooltip: {//提示组件
            	show:true,//是否展示提示框组件  默认 true
            	trigger:'axis',//触发的方式    axis 轴触发
            	// a 系列名称   b 数据类目值  c  数据值  ： 销量 ：
            	formatter:'{a0}<br /><b style="color:red">{b0}：{c0}</b><br/>{a1}<br /><b style="color:red">{b1}：{c1}</b>',//提示框  提示的内容的格式
            },
            legend: {
                data:['销量','利润'],//图例数据
                orient:'vertical',//图例的排列方式  水平  和 垂直
                left :'80%'  //具体左侧的距离  默认 auto
            },
            xAxis: { //轴
                data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
            },
            yAxis: {},//y轴
            series:[ {//图表系列
                name: '销量',
                type: 'bar',
                data: [5, 20, 36, 10, 10, 20]
            },
            {//系列
                name: '利润',
                type: 'line',
                data: [5, 20, 36, 10, 10, 20]
            }]
	};
	//5. 根据配置项绘制图表
	echart.setOption(opt);

</script>
</body>
</html>