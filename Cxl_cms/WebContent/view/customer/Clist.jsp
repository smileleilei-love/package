<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 引入了jstl -->
	<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>客户列表</title>
<!-- 相对路径进行引入 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/layui/css/layui.css"/>
</head>
<body>
	<div>
		<form class="layui-form layui-form-pane" >
			<div class="layui-form-item">
			  <div class="layui-inline" >
					<label class="layui-form-label">业务员:</label>
					<div class="layui-input-inline" style="width: 100px;">
						<select id="userId" >
							<option value="">业务员</option>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">客户名称:</label>
					<div class="layui-input-inline" style="width: 100px;">
						<input id="custName" placeholder="客户名称"  class="layui-input"/>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label" >客户公司:</label>
					<div class="layui-input-inline" style="width: 100px;">
						<input id="company" placeholder="客户公司"  class="layui-input"/>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">性别:</label>
					<div class="layui-input-inline" style="width: 100px">
						<select id="sex">
							<option value="">性别</option>
							<option value="1">男</option>
							<option value="2">女</option>
						</select>
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">客户职位:</label>
					<div class="layui-input-inline"style="width: 100px;">
						<input id="position" placeholder="客户职位"  class="layui-input"/>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">创建时间</label>
			      	<div class="layui-input-inline" style="width: 155px;">
			        	<input type="text" readonly="readonly" id="minCreateTime" placeholder="最小时间" autocomplete="off" class="layui-input">
			      	</div>
			      	<div class="layui-form-mid">-</div>
			      	<div class="layui-input-inline" style="width: 155px;">
			        	<input type="text" readonly="readonly" id="maxCreateTime" placeholder="最大时间" autocomplete="off" class="layui-input">
			      	</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">状态:</label>
					<div class="layui-input-inline"  style="width: 100px;">
						<select id="state">
							<option value="">状态</option>
							<option value="1">有效</option>
							<option value="2">无效</option>
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

<!-- 头工具栏按钮 -->
<script type="text/html" id="headBtns">
	

	<div class="layui-btn-group">
		<c:if test="${user.role == 1}">
			<button class="layui-btn layui-btn-sm" lay-event="add" title="添加">
				<i class="layui-icon layui-icon-add-1"></i>
			</button>
		</c:if>
		<!-- 如果是管理员 -->
		<c:if test="${user.role == 1}">
			<button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="setSalesman" title="设置业务员">
				<i class="layui-icon layui-icon-release"></i>
			</button>
		</c:if>
	</div>
</script>

<!-- 行工具栏按钮 -->
<script type="text/html" id="rowBtns">
	<c:if test="${user.role == 2}">
	<div class="layui-btn-group">
		<button class="layui-btn layui-btn-warm layui-btn-sm" lay-event="edit" title="编辑">
			<i class="layui-icon layui-icon-edit "></i>编辑
		</button>
		<button class="layui-btn  layui-btn-sm" lay-event="visit" title="拜访">
			<i class="layui-icon layui-icon-group"></i>拜访
		</button>
	</div>
	</c:if>
	<c:if test="${user.role == 1}">
	<div class="layui-btn-group">
		<button class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delete" title="删除">
			<i class="layui-icon layui-icon-delete "></i>删除
		</button>
	</div>
	</c:if>

</script>

<script type="text/html" id="editTempl">
	<form class="layui-form layui-form-pane" lay-filter="editFormFilter" style="margin-top:15px;margin-left:30px">
		<div class="layui-form-item">
			<label class="layui-form-label">
				客户:
			</label>
			<div class="layui-input-inline">
				<input name="custName" autocomplete="off" class="layui-input" lay-verify="required"  lay-reqText="请输入客户名" placeholder="客户名"/>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">
				公司:
			</label>
			<div class="layui-input-inline">
				<input name="company"  autocomplete="off" class="layui-input" lay-verify="required"  lay-reqText="请输入公司" placeholder="公司"/>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">
				职位:
			</label>
			<div class="layui-input-inline">
				<input name="position" autocomplete="off" class="layui-input" lay-verify="required"  lay-reqText="请输入职位 " placeholder="职位"/>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">
				工资:
			</label>
			<div class="layui-input-inline">
				<input name="salary" autocomplete="off" class="layui-input" lay-verify="required|number"  lay-reqText="请输入工资 " placeholder="工资"/>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">
				电话:
			</label>
			<div class="layui-input-inline">
				<input name="phone" autocomplete="off" class="layui-input" lay-verify="required|phone"  lay-reqText="请输入电话 " placeholder="电话"/>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">
				性别:
			</label>
			<div class="layui-input-inline">
				<input name="sex" type="radio" value="1" checked="checked" title="男" />
				<input name="sex" type="radio" value="2"   title="女" />
			</div>
		</div>		
																			
		<button type="button"   id="subBtn" lay-filter="subBtnFilter" lay-submit   style="display:none">提交按钮</button>
	</form>
</script>
<!-- 设置业务员模板 -->
<script type="text/html" id="setSalesmanTempl">
	<form class="layui-form layui-form-pane" style="margin-top:15px;margin-left:30px" >
		 <div class="layui-form-item">
			<label class="layui-form-label">业务员:</label>
			<div class="layui-input-inline" >
				<select id="editUserId" name="userId" lay-verify="required" lay-reqText="请选择业务员">
					<option value="">业务员</option>
				</select>
			</div>
		</div>
		<button type="button"   id="subBtn" lay-filter="subBtnFilter" lay-submit   style="display:none">提交按钮</button>
	</form>
</script>
<!-- 新增拜访记录模板 -->
<script type="text/html" id="addVisitLogTempl">
<form class="layui-form layui-form-pane" lay-filter="editFormFilter" style="margin-top:15px;margin-left:30px">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">
					客户:
				</label>
				<div class="layui-input-inline">
					<input name="custName" readonly class="layui-input" lay-verify="required"  lay-reqText="请输入客户名" placeholder="客户名"/>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">
					拜访时间:
				</label>
				<div class="layui-input-inline">
					<input name="visitTime" id="visitTime"  readonly  class="layui-input" lay-verify="required"  lay-reqText="请输入拜访时间" placeholder="拜访时间"/>
				</div>
			</div>
		</div>
		<div class="layui-form-item layui-form-text" style="width:625px">
			<label class="layui-form-label">
				拜访内容:
			</label>
			<div class="layui-input-block" >
				 <textarea name="visitDesc" placeholder="请输入内容" class="layui-textarea"></textarea>
			</div>
		</div>
		<button type="button"   id="subBtn" lay-filter="subBtnFilter" lay-submit   style="display:none">提交按钮</button>
	</form>
</script>


<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script type="text/javascript">
	layui.use(['form','table','jquery','layer','laydate'],function(){
		var form = layui.form;
		var table = layui.table;
		var $ = layui.jquery;
		var layer = layui.layer;
		var laydate = layui.laydate;
		laydate.render({elem:"#minCreateTime"});
		laydate.render({elem:"#maxCreateTime"});
		
		//渲染数据表格
		var insTab = table.render({
			id:"dataTableId",
			elem:"#dataTable",
			url:"${pageContext.request.contextPath}/customer.do?service=page",//获取用户列表数据的接口地址
			toolbar:"#headBtns",//头工具栏事件
			page:true,//开启分页
			height:"400px",
			width:"",
			cols:[[
				{type:"checkbox",width:40},
				{title:"名称",field:"custName",width:80},
				{title:"公司",field:"company",width:120},
				{title:"职位",field:"position",width:120},
				{title:"月薪",field:"salary",width:80},
				{title:"电话",field:"phone",width:120},
				{title:"性别",field:"sex",width:60,templet:function(d){
					var sex = d.sex;
					if(sex == 1){
						return "<b style='color:red'>男</b>";
					}else if(sex == 2){
						return "<b style='color:green'>女</b>";
					}
				}},
				{title:"状态",field:"state",width:60,templet:function(d){
					var state = d.state;
					if(state == 1){
						return "<b style='color:green'>有效</b>";
					}else if(state == 2){
						return "<b style='color:blue'>无效</b>";
					}
				}},
				{title:"业务员",field:"realName",width:100},
				{title:"创建时间",field:"createTime",width:170},
				{title:"操作",toolbar:"#rowBtns",width:190}
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
			var userId = $("#userId").val();
			var custName = $("#custName").val();
			var company = $("#company").val();
			var sex = $("#sex").val();
			var position = $("#position").val();
			var minCreateTime = $("#minCreateTime").val();
			var maxCreateTime = $("#maxCreateTime").val();
			var state = $("#state").val();
			//数据重载
			insTab.reload({
				where:{
					userId : userId,
					custName : custName,
					company : company,
					sex : sex,
					position : position,
					minCreateTime : minCreateTime,
					maxCreateTime : maxCreateTime,
					state:state
				}
			});
		});
		
		//1. 获取所有的业务员
		function initSalesman(id){
			$.get("${pageContext.request.contextPath}/user.do?service=getAllSalesman",function(rs){
				if(rs.code != 200){
					layer.msg("业务员获取失败");
					return false;
				}
				//渲染下拉框
				renderSelect(id,rs.data);
			})
		}
		//2. 将业务员数据组装成dom 放入 select中
		//将数据放入到指定的select中
		function renderSelect(id,data){
			var select = $("#"+id);//获取select
			var options = "";
			$.each(data,function(index,value){
				options = options + "<option  value='"+value.id+"'>"+value.realName+"</option>";
			});
			select.append(options);
			//重新渲染
			form.render("select");
		}
		initSalesman("userId");//调用
		
//头工具栏监听事件----------------------------------------------------------------------
//---------------------------------------------------------------------------------
		table.on("toolbar(dataTableFilter)",function(d){
			var event = d.event;
			if(event == "add"){//新增客户
				add();
			}else if(event == "setSalesman"){
				setSalesman();//批量修改客户的业务员
			}
			
		})
		//新增客户
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
						$.post("${pageContext.request.contextPath}/customer.do?service=add",formData,function(rs){
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
		
		//批量修改客户的业务员
		function setSalesman(){
			//获取选中的数据
			var checkedData = table.checkStatus('dataTableId'); //
			var data = checkedData.data;
			if(checkedData == null || data.length == 0){
				layer.msg("请先选中要修改的客户");
				return false;
			}
			var ids = "";//所有客户的ID
			//获取所有要修改的数据的ID 
			$.each(data,function(i,v){
				ids = ids + "id="+v.id+"&";//id=1&id=2&.....
			});
			layer.open({
				title:"编辑",
				type:1,//页面层
				content:$("#setSalesmanTempl").html(),
				area:['365px','450px'],//设置宽高
				btn:['确认','取消'],
				btnAlign:"c",
				yes:function(index,layero){
					//点击表单中提交按钮
					$("#subBtn").click();
				},
				success:function(layero,index){//弹出层完成后执行的函数
					initSalesman("editUserId");//调用 为下拉框添加 option
					form.render("select");//渲染单选按钮
					//为form表单提交按钮设置监听
					form.on("submit(subBtnFilter)",function(d){
						var formData = d.field;//获取表单数据  业务员ID
						$.post("${pageContext.request.contextPath}/customer.do?service=setSalesman&"+ids+"userId="+formData.userId,function(rs){
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
		
//行工具栏监听事件----------------------------------------------------------------------
//---------------------------------------------------------------------------------
		table.on("tool(dataTableFilter)",function(d){
			var event = d.event;//事件
			var data = d.data;//数据
			if(event == "edit"){
				edit(data);//进行编辑
			}else if(event == "visit"){
				addVisit(data);
			}else if(event == "delete"){
				del(data);//删除
			}
			
			
		});
		//修改客户信息
		function edit(data){
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
					//对form表单赋值
					form.val("editFormFilter",data);
					form.render("radio");//渲染单选按钮
					//为form表单提交按钮设置监听
					form.on("submit(subBtnFilter)",function(d){
						var formData = d.field;//获取表单数据
						formData.id = data.id;//为对象id赋值 用于修改
						$.post("${pageContext.request.contextPath}/customer.do?service=update",formData,function(rs){
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
		//新增客户的拜访记录
		function addVisit(d){
			layer.open({
				title:"拜访",
				type:1,//页面层
				content:$("#addVisitLogTempl").html(),
				area:['690px','450px'],//设置宽高
				btn:['确认','取消'],
				btnAlign:"c",
				yes:function(index,layero){
					//点击表单中提交按钮
					$("#subBtn").click();
				},
				success:function(layero,index){//弹出层完成后执行的函数
					laydate.render({elem:"#visitTime"});
					//对form表单赋值
					form.val("editFormFilter",d);
					//为form表单提交按钮设置监听
					form.on("submit(subBtnFilter)",function(data){
						var formData = data.field;//获取表单数据
						formData.custId = d.id;//为对象id赋值 用于修改
						$.post("${pageContext.request.contextPath}/visit.do?service=add",formData,function(rs){
							if(rs.code != 200){//异常时 展示异常原因
								layer.msg(rs.msg);
								return false;
							}
							//关闭弹出层
							layer.close(index);
						});
					});
				}
			});
		}
		
		//删除客户
		function del(data){
			if(data.state == 2){
				layer.msg("客户已经删除！")
				return false;
			}
			layer.confirm("确定要删除“ "+data.custName+"” 吗?",function(index){
				//将数据对数据库数据 密码改成初始密码 
				$.post("${pageContext.request.contextPath}/customer.do?service=delete",{id:data.id},function(rs){
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
		
	});
</script>
</body>
</html>