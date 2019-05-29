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
${userList}
	<div>
		<table id="userTable" lay-filter="table"></table>
	</div>
	<script type="text/javascript" src="layui/layui.js"></script>
	<script type="text/javascript">
		layui.use('table',function(){
			var table=layui.table;
			var datas=${userList};
			//第一个实例
			  table.render({
			    elem: '#userTable'
			    ,height: 312
			    ,url: '<%=basePath%>findAll' //数据接口
			    ,page: true //开启分页
			    ,cols: [[ //表头
			      {field: 'userid', title: 'ID', width:80, sort: true, fixed: 'left'}
			      ,{field: 'username', title: '用户名', width:80}
			      ,{field: 'realname', title: '性别', width:80, sort: true}
			      ,{field: 'password', title: '城市', width:80} 
			      ,{field: 'createTime', title: '签名', width: 177}
			      ,{field: 'phone', title: '积分', width: 80, sort: true}
			      ,{field: 'createUser', title: '评分', width: 80, sort: true}
			      ,{field: 'email', title: '职业', width: 80}
			      ,{field: 'status', title: '财富', width: 135, sort: true}
			    ]]
			    ,data:datas
			  });
		});
	</script>



</body>
</html>