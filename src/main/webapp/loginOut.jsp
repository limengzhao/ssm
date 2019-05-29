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
<body>
	<div class="window">
		<h1>退出成功</h1>
	</div>
	<script type="text/javascript" src="layui/layui.js"></script>

	<script type="text/javascript">
		layui.use([ 'layer', 'form' ], function() {
			var layer = layui.layer, form = layui.form;	
		});
	</script>
</body>
</html>
