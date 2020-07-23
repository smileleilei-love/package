<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!-- 引入了jstl -->
	<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="resources/layui/css/layui.css"/>
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin" >
		<div class="layui-header">
			<div class="layui-logo">CMS管理系统</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item">
					<a href="javascript:;"> 
						<img onerror="javascript:this.src='imgs/default.jpg';"	src="${user.img}" class="layui-nav-img"> ${user.realName}
					</a>
				</li>
				<li class="layui-nav-item"><a href="user.do?service=loginOut">退出</a></li>
			</ul>
		</div>
		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
						<li class="layui-nav-item layui-nav-itemed">
							<a class="" href="javascript:;">基础信息</a>
							<dl class="layui-nav-child">
								<!-- 如果是管理员 -->
								<c:if test="${user.role == 1}">
									<dd>
										<a href="view/user/Ulist.jsp" target="cms-content">员工管理</a>
									</dd>
								
									<dd>
										<a href="view/customer/Clist.jsp" target="cms-content">客户管理</a>
									</dd>
								</c:if>
								<c:if test="${user.role == 2}">
								<dd>
									<a href="view/customer/Clist.jsp" target="cms-content">查看客户</a>
								</dd>
								</c:if>
							</dl>
						</li>
					<li class="layui-nav-item layui-nav-itemed"><a href="javascript:;">系统管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="view/user/updatePassword.jsp" target="cms-content">修改密码</a>
							</dd>
							<dd>
								<a href="view/user/updateImg.jsp" target="cms-content">修改头像</a>
							</dd>
						</dl>
					</li>
					<c:if test="${user.role == 1}">
						<li class="layui-nav-item layui-nav-itemed"><a href="javascript:;">统计分析</a>
						 <dl class="layui-nav-child">
								<dd>
									<a href="view/statistics/pie.jsp" target="cms-content">拜访占比</a>	
								</dd>
							</dl>
						</li>
					</c:if>
				</ul>
			</div>
		</div>

		<div class="layui-body" style="width: 100%;height: 100%">
			<!-- 内容主体区域 -->
			<div style="width: 1150px;height: 550px" >
				<iframe name="cms-content" style="width: 100%;height: 100%;border: 0px;padding:15px"></iframe>
			</div>
		</div>

		<div class="layui-footer" style="text-align: center;ling-height:20px" >
			<!-- 底部固定区域 -->
			© 程晓磊 - 河北农业大学
		</div>
	</div>
	<script src="resources/layui/layui.js"></script>
	<script>
	//JavaScript代码区域
	layui.use('element', function(){
	  var element = layui.element;
	  
	});
</script>
</body>
</html>