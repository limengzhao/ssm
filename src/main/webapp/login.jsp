<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>登录</title>
<link rel="stylesheet" href="layui/css/layui.css">
<style type="text/css">
.window {
	padding-left: 500px;
	padding-top: 150px;
	width: 300px;
	height: 200px;
}
</style>
</head>
<body style="background-image: url('layui/images/login/login.jpg');">
	<div class="window">
		<div style="border: 1px solid gray;">
			<div class="admin-login-background"
				style="margin-top: 20px; margin-left: 30px; margin-right: 30px; margin-bottom: 30px">
				<form class="layui-form layui-form-pane" action="<%=basePath%>login" method="post">
					<div class="layui-form-item">
						<i
							class="layui-icon layui-icon-username admin-icon admin-icon-username">&nbsp;用户名:</i>
						<input type="text" name="username" placeholder="请输入用户名"
							autocomplete="off" class="layui-input">
					</div>
					<div>
						<i
							class="layui-icon layui-icon-password admin-icon admin-icon-password">&nbsp;密&nbsp;&nbsp;&nbsp;码:</i>
						<input type="password" name="password" placeholder="请输入密码"
							autocomplete="off" class="layui-input admin-input">
					</div>
					<div class="layui-form-item"
						style="text-align: center; margin-top: 30px">
						
						<input type="submit"  class="layui-btn" value="登录"/>
						<a href="<%=basePath%>register?type=login" class="layui-btn">注册</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="layui/layui.js"></script>

	<script type="text/javascript">
		layui.use([ 'layer', 'form','jquery'], function() {
			var layer = layui.layer;
			var form = layui.form;	
			var $=layui.jquery;
			
			form.on('onClick(register)',function(data){
				alert(data.field.username);
				//alert(data.field.username);
				
				//ajax访问后台
			});
			
		});
		
		
	</script>
</body>
</html>
