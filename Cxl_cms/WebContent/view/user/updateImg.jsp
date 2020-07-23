<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改头像页面</title>
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
		<label class="layui-form-label">头像:</label>
		<div class="layui-input-inline">
			<img   onerror="javascript:this.src='${pageContext.request.contextPath}/imgs/default.jpg';" id="userImg" src="${pageContext.request.contextPath}/${user.img}" style="width:190px;height:200px;border: 1px solid #e2e2e2" />
			<button class="layui-btn" id="uploadBtn"  type="button" style="width:190px;margin-top: 40px; ">上传</button>
		</div>
	</div>
</form>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script type="text/javascript">
	layui.use(['jquery','layer','upload'],function(){
		var $ = layui.jquery;
		var layer = layui.layer;
		var upload = layui.upload;
		//初始化参数
		var opt = {
			elem:"#userImg",//与生成的file文件域关联dom元素
			url:"${pageContext.request.contextPath}/user.do?service=upload",//服务器接收文件数据的地址
			accept:"images",//允许上传的文件的类型  默认是 images 图片
			acceptMime:'image/*',//文件选择框 支持的类型  只显示图片
			exts:'jpg',//允许上传的文件的后缀  只允许jpg格式
			auto:false,//不允许自动上传  不会主动的提交数据给url地址  需要手动触发
			bindAction: '#uploadBtn',//绑定提交的dom元素 点击这个绑定的dom元素 才会提交数据
			field:"userImg",//设置文件域的字段名称  用于后台获取文件数据
			size:1024,//允许文件上传的最大尺寸 单位KB  1M
			choose:function(obj){//选择文件后调用的函数
				//文件预览的方法
				obj.preview(function(index,file,result){
					//index  文件的索引  多文件上传时
					//file   文件转化的file 对象
					//result  文件转化为 base64 字符串
					$("#userImg").attr("src",result);//将base64字符串设置为 图片src的属性值
				});
			},
			done:function(rs,index,upload){//上传完成的回调
				if(rs.code != 200){
					layer.msg("头像修改失败!");
					return false;
				}
				layer.alert("图像修改成功!重新登录",function(index){
					//重新登录
					window.parent.location.href = "${pageContext.request.contextPath}/user.do?service=loginOut";
				})
			}
		};
		upload.render(opt);
	});
</script>
</body>
</html>