<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<meta http-equiv="Pragma" content="no-cache"> 
<meta http-equiv="Cache-Control" content="no-cache"> 
<meta http-equiv="Expires" content="0"> 
<title>后台登录</title> 
<link rel="stylesheet" type="text/css" href="resources/layui/css/layui.css"/>
<link href="resources/css/login.css" type="text/css" rel="stylesheet"> 

</head> 
<body> 

<div class="login">
    <div class="message">管理登录</div>
    <div id="darkbannerwrap"></div>
    <form method="post" class="layui-form">
    	<div class="layui-form-item">		
			<input name="username" lay-verify="required" lay-reqText="请输入用户名" placeholder="用户名" class="layui-input" type="text">
		</div>
		<div class="layui-form-item">
			<input name="password" lay-verify="required" lay-reqText="请输入密码"  placeholder="密码" class="layui-input" type="text">
		</div>
		<div id="slider"></div>
		<hr class="hr15" style="background-color: #fbfbfb">
		<input value="登录" style="width:100%;" type="button" lay-filter="sbumitBtnFilter" lay-submit />
	</form>
</div>

<div class="copyright">© 河北农业大学 </div>
<script type="text/javascript" src="resources/layui/layui.js"></script>
<script type="text/javascript">
	layui.config({
		base: 'resources/sliderVerify/' //引入扩展的js组件
	}).use(['sliderVerify','form','layer','jquery'],function(){
		var sliderVerify = layui.sliderVerify;
		var form = layui.form;
		var layer = layui.layer;
		var $ = layui.jquery;
		//初始化滑块
		var slider =  sliderVerify.render({
			elem:"#slider",
			isAutoVerify:true,//自动验证
		});
		//表单提交监听
		form.on("submit(sbumitBtnFilter)",function(d){
			var data = d.field;//获取表单数据
			$.post("user.do?service=login",data,function(rs){
				
				//rs -->{"code":4001002,"msg":"用户账号已失效"}
				//登录不成功  
				if(rs.code != 200){
					layer.msg(rs.msg);//将异常原因展示
					return false;
				}
				location.href = "main.jsp";//跳转到主页面
			})
			slider.reset();//重置滑块
			return false;//阻止表单默认提交
		})
		
	}); 
</script>

</body>
</html>