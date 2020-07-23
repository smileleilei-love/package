<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>员工列表</title>
<!-- 相对路径进行引入 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/layui/css/layui.css"/>
</head>
<body>
	<div>
		<form class="layui-form layui-form-pane" >
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">用户名:</label>
					<div class="layui-input-inline" style="width: 100px">
						<input id="username" placeholder="用户名"  class="layui-input"/>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">姓名:</label>
					<div class="layui-input-inline" style="width: 100px">
						<input id="realName" placeholder="姓名"  class="layui-input"/>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label" >角色:</label>
					<div class="layui-input-inline" style="width: 100px">
						<select id="role">
							<option value="">角色</option>
							<option value="1">管理员</option>
							<option value="2">业务员</option>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">状态:</label>
					<div class="layui-input-inline"  style="width: 100px">
						<select id="state">
							<option value="">状态</option>
							<option value="1">在职</option>
							<option value="2">离职</option>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<button class="layui-btn" id="searchBtn" type="button">查询</button>
					<button class="layui-btn layui-btn-primary" type="reset">清空</button>
				</div>
			</div>
		</form>
		<hr class="layui-bg-green">
		<table id="dataTable" lay-filter="dataTableFilter"></table>
	</div>
	
	
<script type="text/html" id="editTempl">
	<form class="layui-form layui-form-pane" lay-filter="editFormFilter" style="margin-top:15px;margin-left:30px">
		<div class="layui-form-item">
			<label class="layui-form-label">
				用户名:
			</label>
			<div class="layui-input-inline">
				<input name="username" autocomplete="off" class="layui-input" lay-verify="required"  lay-reqText="请输入用户名" placeholder="用户名"/>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">
				密码:
			</label>
			<div class="layui-input-inline">
				<input name="password" value="123456" readonly autocomplete="off" class="layui-input" lay-verify="required"  lay-reqText="请输入用密码" placeholder="密码"/>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">
				姓名:
			</label>
			<div class="layui-input-inline">
				<input name="realName" autocomplete="off" class="layui-input" lay-verify="required"  lay-reqText="请输入用户姓名 " placeholder="用户姓名"/>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">
				角色:
			</label>
			<div class="layui-input-inline" style="width:210px">
				<input name="role" type="radio" value="1"  title="管理员" />
				<input name="role" type="radio" value="2" checked="checked"  title="业务员" />
			</div>
		</div>																			
		<button type="button"   id="subBtn" lay-filter="subBtnFilter" lay-submit   style="display:none">提交按钮</button>
	</form>
</script>
<!-- 头工具栏按钮 -->
<script type="text/html" id="headBtns">
	<div class="layui-btn-group">
		<button class="layui-btn layui-btn-sm" lay-event="add" title="添加">
			<i class="layui-icon layui-icon-add-1"></i>
		</button>
	</div>
</script>

<!-- 行工具栏按钮 -->
<script type="text/html" id="rowBtns">
	<div class="layui-btn-group">
		<button class="layui-btn layui-btn-warm layui-btn-sm" lay-event="reset">
			<i class="layui-icon layui-icon-refresh "></i>重置密码
		</button>
		<button class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delete">
			<i class="layui-icon layui-icon-delete "></i>离职
		</button>
	</div>

</script>

<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script type="text/javascript">
	layui.use(['form','table','jquery','layer'],function(){
		var form = layui.form;
		var table = layui.table;
		var $ = layui.jquery;
		var layer = layui.layer;
		//渲染数据表格
		var insTab = table.render({
			id:"dataTableId",
			elem:"#dataTable",
			url:"${pageContext.request.contextPath}/user.do?service=page",//获取用户列表数据的接口地址
			toolbar:"#headBtns",//头工具栏事件
			page:true,//开启分页
			height:"450px",
			cols:[[
				{type:"checkbox",width:80},
				{title:"用户名",field:"username",width:120},
				{title:"密码",field:"password",width:120},
				{title:"姓名",field:"realName",width:120},
				{title:"角色",field:"role",width:120,templet:function(d){
					var role = d.role;
					if(role == 1){
						return "<b style='color:red'>管理员</b>";
					}else if(role == 2){
						return "<b style='color:green'>业务员</b>";
					}
				}},
				{title:"状态",field:"state",width:120,templet:function(d){
					var state = d.state;
					if(state == 1){
						return "<b style='color:blue'>在职</b>";
					}else if(state == 2){
						return "<b style='color:gray'>离职</b>";
					}
				}},
				{title:"创建时间",field:"createTime",width:200},
				{title:"操作",toolbar:"#rowBtns",width:260}
			]],//列信息
			parseData:function(rs){//数据格式的适配
				return {
					"code":rs.code,
					"msg":rs.msg,
					"count":rs.data.count,
					"data":rs.data.data
				}
			},
			response:{
				statusCode:200
			}
		});
		//搜索
		$("#searchBtn").click(function(){
			var username = $("#username").val();
			var realName = $("#realName").val();
			var role = $("#role").val();
			var state = $("#state").val();
			//数据重载
			insTab.reload({
				where:{
					username : username,
					realName : realName,
					role : role,
					state:state
				}
			});
		});
		
		//行工具栏监听事件
		table.on("tool(dataTableFilter)",function(d){
			var event = d.event;
			var data = d.data;
			if(event == "reset"){//重置密码
				reset(data);
			}else if(event == "delete"){//离职
				del(data)
			}
		});	
		
		//重置密码
		function reset(data){
			layer.confirm("确定要重置: "+data.realName+" 的密码吗?",function(index){
				//将数据对数据库数据 密码改成初始密码 
				$.post("${pageContext.request.contextPath}/user.do?service=resetPwd",{id:data.id},function(rs){
					if(rs.code != 200){
						layer.msg(rs.msg);//将异常信息提示
						return false;
					}
					//重置成功表格数据要刷新
					//点击查询按钮 刷新数据表格
					$("#searchBtn").click();
					//关闭弹出层
					layer.close(index);
				});
			})
		}
		
		//员工离职
		function del(data){
			if(data.state == 2){
				layer.msg("员工已经离职")
				return false;
			}
			layer.confirm("确定:"+data.realName+" 要离职吗?",function(index){
				//将数据对数据库数据 密码改成初始密码 
				$.post("${pageContext.request.contextPath}/user.do?service=delete",{id:data.id},function(rs){
					if(rs.code != 200){
						layer.msg(rs.msg);//将异常信息提示
						return false;
					}
					//重置成功表格数据要刷新
					//点击查询按钮 刷新数据表格
					$("#searchBtn").click();
					//关闭弹出层
					layer.close(index);
				});
			})
		}
		
		//头工具栏监听事件
		table.on("toolbar(dataTableFilter)",function(d){
			var event = d.event;
			if("add" == event){
				add();//调用add方法
			}
			
		});
		//新增员工信息
		function add(){
			layer.open({
				title:"编辑",
				type:1,//页面层
				content:$("#editTempl").html(),
				area:['365px','450px'],//设置宽高
				btn:['确认','取消'],
				btnAlign:"c",
				yes:function(index,layero){
					//点击表单中提交按钮
					$("#subBtn").click();
				},
				success:function(layero,index){//弹出层完成后执行的函数
					form.render("radio");//渲染单选按钮
					//为form表单提交按钮设置监听
					form.on("submit(subBtnFilter)",function(d){
						var formData = d.field;//获取表单数据
						
						$.post("${pageContext.request.contextPath}/user.do?service=add",formData,function(rs){
							if(rs.code != 200){//异常时 展示异常原因
								layer.msg(rs.msg);
								return false;
							}
							//重新加载数据表格
							$("#searchBtn").click();
							//关闭弹出层
							layer.close(index);
						});
					});
				}
				
			});
		}
		
		
	});


</script>
</body>
</html>