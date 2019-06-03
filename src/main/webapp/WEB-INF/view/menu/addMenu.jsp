<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加菜单</title>
<link rel="stylesheet" href="layui/css/layui.css">
<script type="text/javascript" src="layui/layui.js"></script>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-row">
			<h1 style="text-align: center; margin-top: 30px;">菜单添加</h1>
		</div>
		<div class="layui-row" style="margin-top: 50px;">
			<form class="layui-form" method="post">
				<div class="layui-form-item">
				<label class="layui-form-label">输入框</label>
				<div class="layui-input-block">
      			<input type="text" style="width: 200px;" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
    			</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>