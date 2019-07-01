<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
<link rel="stylesheet" href="layui/css/layui.css" media="all">
</head>
<body>
	<div class="demoTable">
		搜索ID：
		<div class="layui-inline">
			<input class="layui-input" name="id" id="demoReload"
				autocomplete="off">
		</div>
		<button class="layui-btn" data-type="reload">搜索</button>
	</div>
	<div>
		<table id="roleTable" class="layui-table" lay-size="sm"
			lay-filter="table"></table>
		<script type="text/html" id="roleToolbar">
  			<div class="layui-btn-container">
    			<button class="layui-btn" lay-event="addRole">新     增</button>
			</div>
</script>
	</div>
	<script type="text/javascript" src="layui/layui.js"></script>
	<script type="text/javascript">
		layui.use(['table','jquery','layer'],function(){
		var table=layui.table;
		var layer=layui.layer;
		var $=layui.$;
	
	//第一个实例
	  table.render({
	    elem: '#roleTable'
	    ,id:'roleTableReload'
	    ,height:540
	    ,title:'角色管理'
		,width:1000
		,limit:10
	    ,url: '<%=basePath%>findRoleData' //数据接口
		,toolbar : '#roleToolbar'
		,page : true //开启分页
		,cols : [ [ //表头
			{
				field : 'roleId',
				title : 'ID',
				width : 135,
				sort : true
				}, {
					field : 'roleName',
					title : '角色名称',
					width : 135
				}, {
					field : 'roleDescription',
					title : '角色描述',
					width : 135
				}, {
					field : 'roleStatus',
					title : '角色状态',
					width : 135,
					templet:'#viewStatus'
				}, {
					field : 'regDate',
					title : '注册日期',
					width : 135
				}, {
					title : '操作',
					width : 150,
					align : 'center',
					toolbar : '#barOperator'
				} ] ],
				done : function(res, curr, count) {
					//如果是异步请求数据方式，res即为你接口返回的信息。
					//如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
					console.log(res);

					//得到当前页码
					console.log(curr);

					//得到数据总量
					console.log(count);
				}
			});
			//表格重载
			active = {
				reload : function() {
					var demoReload = $('#demoReload');
					//执行重载
					table.reload('roleTableReload', {
						page : {
							curr : 1
						//重新从第 1 页开始
						},
						where : {
							roleId : demoReload.val()
						}
					});
				},
			};
			$('.demoTable .layui-btn').on('click', function() {
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});
			//监听按钮事件
			table.on('toolbar(table)',function(obj){
				if(obj.event=="addRole"){
					layer.open({
						type:2,
						title:'添加角色',
						content:'<%=basePath%>addRolePage',
						area:["80%","80%"]
					});
				}
			});
			//监听工具
			  table.on('tool(table)',function(obj){
				  var data = obj.data;
				  var roleId={
							 "roleId":data.roleId
					  }
				  if(obj.event=="updateRole"){
						layer.open({
							type:2,
							title:'更新角色',
							content:'<%=basePath%>findUpdateRolePage?roleId='+data.roleId,
							area:["80%","80%"]
						});
					}else if(obj.event=="delRole"){
						layer.confirm('确认删除？',function(index){
							  obj.del();
							  layer.close(index);
							  $.ajax({
								  	url:'<%=basePath%>deleteRole',
									data:roleId,
									type:'get',
									success:function(data){
										if(data=="true"){
											layer.msg('删除操作成功');
										}else{
											layer.msg('删除操作失败');
										}
									}
							  });
							  
						  });
					}
			  });
		});
	</script>
	<script type="text/html" id="barOperator">
       <a class="layui-btn layui-btn-xs" lay-event="updateRole">更新</a>
       <a  class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delRole">删除</a>
    </script>
    <script type="text/html" id="viewStatus">
		{{# if (d.roleStatus=== '0') { }}   
  		启用
		{{# } else if(d.roleStatus=== '1') { }}  
  		禁用
		{{# } else { }}  
  		未知
		{{# } }}
	</script>
</body>
</html>