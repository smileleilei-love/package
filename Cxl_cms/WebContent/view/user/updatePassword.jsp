<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改密码页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/layui/css/layui.css"/>
</head>
<body>
<form class="layui-form layui-form-pane">
	<div class="layui-form-item">
		<label class="layui-form-label">姓名:</label>
		<div class="layui-input-inline">
			<input class="layui-input" value="${user.realName}"  readonly/>
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">用户名:</label>
		<div class="layui-input-inline">
			<input class="layui-input" value="${user.username}"  readonly/>
		</div>
	</div>		
	<div class="layui-form-item">
		<label class="layui-form-label">原密码:</label>
		<div class="layui-input-inline">
			<input class="layui-input" name="password" lay-verify="required" lay-reqText="请输入原密码"  placeholder="原密码"  autocomplete="false"/>
		</div>
	</div>	
	<div class="layui-form-item">
		<label class="layui-form-label">新密码:</label>
		<div class="layui-input-inline">
			<input class="layui-input" name="newPassword" lay-verify="required" lay-reqText="请输入新密码"  placeholder="新密码"  autocomplete="false"/>
		</div>
	</div>		
	<div class="layui-form-item">
		<label class="layui-form-label">确认密码:</label>
		<div class="layui-input-inline">
			<input class="layui-input" name="confirmPassword"    lay-verify="required" lay-reqText="请再次输入新密码"  placeholder="再次输入新密码"  autocomplete="false"/>
		</div>
	</div>	
	<div class="layui-form-item" style="margin-top: 40px;padding-left: 40px">
		<button class="layui-btn" lay-submit lay-filter="subBtnFilter" type="button">确认</button>
		<button style="margin-left: 40px;" class="layui-btn layui-btn-primary"  type="reset">取消</button>
	</div>
</form>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script type="text/javascript">
	layui.use(['jquery','form','layer'],function(){
		var $ = layui.jquery;
		var form = layui.form;
		var layer = layui.layer;
		//表单提交事件 
		form.on("submit(subBtnFilter)",function(d){
			var formData = d.field;
			//对比两次新密码是否一致
			if(formData.newPassword != formData.confirmPassword){
				layer.msg("两次新密码不一致，请保持一致");
				return false;
			}
			$.post("${pageContext.request.contextPath}/user.do?service=updatePassword",formData,function(rs){
				if(rs.code != 200){
					layer.msg(rs.msg);
					return false;
				}
				layer.alert("密码修改成功!",function(index){
					//重新登录
					window.parent.location.href = "${pageContext.request.contextPath}/user.do?service=loginOut";
				})
				
			});
			
			
			return false;
		});		
		
		
	})


</script>

</body>
</html>