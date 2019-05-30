<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<link rel="stylesheet" href="layui/css/layui.css" media="all">
</head>
<body>
<div class="demoTable">
  搜索ID：
  <div class="layui-inline">
    <input class="layui-input" name="id" id="demoReload" autocomplete="off">
  </div>
  <button class="layui-btn" data-type="reload">搜索</button>
</div>
	<div>
		<table id="userTable" class="layui-table" lay-size="sm" lay-filter="table"></table>
		<script type="text/html" id="userToolbar">
  			<div class="layui-btn-container">
    			<button class="layui-btn" lay-event="addUser">新     增</button>
			</div>
		</script>
	</div>
	<script type="text/javascript" src="layui/layui.js"></script>
	<script type="text/javascript">
		layui.use(['table','jquery','layer'],function(){
			var table=layui.table;
			var $=layui.$;
			//第一个实例
			  table.render({
			    elem: '#userTable'
			    ,id:'userTableReload'
			    ,height:550
			    ,width:1000
			    ,title:'用户管理'
			    ,url: '<%=basePath%>findAll' //数据接口
			    ,toolbar: '#userToolbar'
			    ,page: true //开启分页
			    ,cols: [[ //表头
			      {field: 'userid', title: 'ID', width:80, sort: true, fixed: 'left'}
			      ,{field: 'username', title: '用户名', width:100}
			      ,{field: 'realname', title: '真实姓名', width:100}
			      ,{field: 'password', title: '密码', width:80} 
			      ,{field: 'createTime', title: '创建时间', width:100}
			      ,{field: 'phone', title: '电话', width:100}
			      ,{field: 'createUser', title: '创建人', width:80}
			      ,{field: 'email', title: '邮箱', width:110}
			      ,{field: 'status', title: '状态', width: 80}
			      ,{title: '操作', width:150, align:'center', toolbar: '#barOperator'}
			    ]]
			  });
			//监听按钮事件
			table.on('toolbar(table)',function(obj){
				if(obj.event=="addUser"){
					location.href="<%=basePath%>register?type=add"
				}
			});
			//表格重载
			active = {
					reload: function(){
					      var demoReload = $('#demoReload');
					      //执行重载
					      table.reload('userTableReload', {
					        page: {
					          curr: 1 //重新从第 1 页开始
					        }
					        ,where: {
					        	userid: demoReload.val()
					        }
					      });
					    }
				  };
			$('.demoTable .layui-btn').on('click', function(){
			    var type = $(this).data('type');
			    active[type] ? active[type].call(this) : '';
			  });
			
			//监听工具
			  table.on('tool(table)',function(obj){
				  var data = obj.data;
				  var userid={
							 "userid":data.userid
					  }
				  if(obj.event=="updateUser"){//查看这条数据
					  layer.open({
						  type:2,
						  title:'更新用户',
						  offset: '30px',
						  content:'<%=basePath%>userUpdate?userid='+data.userid,
						  area:["80%","80%"]
					  });
				  
				  }else if(obj.event=="delUser"){
					  layer.confirm('确认删除？',function(index){
						  alert(index+'  '+data.userid);
						  obj.del();
						  layer.close(index);
						  $.ajax({
							  	url:'<%=basePath%>deleteUser',
								data:userid,
								type:'get',
								success:function(data){
									if(data=="0"){
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
       <a class="layui-btn layui-btn-xs" lay-event="updateUser">更新</a>
       <a  class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delUser">删除</a>
    </script>
    
    
	
	
	



</body>
</html>