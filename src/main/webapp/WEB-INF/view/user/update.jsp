<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<title>更新</title>
<link rel="stylesheet" href="layui/css/layui.css">
<script type="text/javascript" src="layui/layui.js"></script>
<style type="text/css">
.layui-col {
	text-align: center;
}
</style>
</head>
<body>
	<div class="layui-container">
		<%-- <div class="layui-row">
			<h1 style="text-align: center; margin-top: 30px;">更新${id}</h1>
		</div> --%>
		<div class="layui-row layui-col-space10" style="margin-top: 10px;">
			<div class="layui-col-md8 layui-col">
				<form class="layui-form layui-form-pane" method="post">
				<input type="hidden" name="type" value="${type}" id="type"/>
					<!--action="<%=basePath%>save" method="post" -->
					<fieldset class="layui-elem-field layui-field-title">
						<legend>更新</legend>
						<div class="layui-field-box" style="margin-left: 200px;">
							<div class="layui-form-item">
								<label class="layui-form-label">用户名</label>
								<div class="layui-input-inline" style="width: 200px;">
									<input id="username" type="text" name="username"
										autocomplete="off" placeholder="请输入用户名" readonly="true" disabled="disabled" class="layui-input" value="${user.username}"/>
								</div>
								<div class="layui-form-mid layui-word-aux">
									<i id="wordtext" style="color: red">不可修改</i>
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">密 码</label>
								<div class="layui-input-inline" style="width: 200px;">
									<input type="password" name="password" autocomplete="off"
										placeholder="请输入密码" class="layui-input" value="${user.password}"/>
								</div>
								<div class="layui-form-mid layui-word-aux">
									<i>辅助文字</i>
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">真实姓名</label>
								<div class="layui-input-block" style="width: 200px;">
									<input type="text" name="realname" autocomplete="off"
										placeholder="请输入真实姓名" class="layui-input" value="${user.realname}"/>
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">状态</label>
								<div class="layui-input-block layui-form" lay-filter="selectFilter" style="width: 200px;">
									<select id="select" name="status" lay-verify="required">
										<option value="">请选择</option>
										<option value="0">已启用</option>
										<option value="1">未启用</option>
									</select>
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">邮箱</label>
								<div class="layui-input-inline" style="width: 200px;">
									<input type="text" name="email" lay-verify="email"
										autocomplete="off" class="layui-input" value="${user.email}"/>
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">手机号</label>
								<div class="layui-input-inline" style="width: 200px;">
									<input type="tel" name="phone" lay-verify="required|phone"
										autocomplete="off" class="layui-input" value="${user.phone}"/>
								</div>
							</div>
						</div>
						<div class="layui-form-item">
							<button type="submit" lay-submit lay-filter="sub"
								class="layui-btn" lay->更新</button>
							<a id="cancel" class="layui-btn">取消</a>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
	
	

	<script type="text/javascript">
		layui.use(['layer','jquery','form'], function() {
			var layer = layui.layer;
			var form = layui.form;	
			var $=layui.jquery;
			//设置下拉框的默认值
			var status='${user.status}';//获取model的值在js中使用
			$('#select').val(status);
			form.render('select','selectFilter'); 
			//判断取消返回主页面
			$("#cancel").on('click',function(){
				//当你在iframe页面关闭自身时
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭 
			});
			//监控用户名是否已经被注册
			<%-- $("#username").blur(function(){
				//var data=$("#username").val();
				if($("#username").val()==""){
					$("#wordtext").html("不可为空").attr("style","color:red;");
				}else{
					var data={
							'username':$("#username").val()
						};
						$.ajax({
							url:'<%=basePath%>username.action',
							data:data,
							type:'post',
							dataType:'json',
							success:function(data){
								if(data=="1"){
									$("#wordtext").html("已存在，不可用").attr("style","color:red;");
								}else if(data=="0"){
									$("#wordtext").html("可用").attr("style","color:red;");
								}
							}
						});
				}	
			}); --%>
			//监控更新按钮
			form.on('submit(sub)',function(data){
				//需要判断输入框是否有值并且符合条件，然后才能提交
				$.ajax({
					url:'<%=basePath%>update',
					method:'post',
					data:data.field,
					success:function(res){
						layer.open({
							type:0,
							content:'更新成功！',
							btn:['确定'],
							yes:function(){
								//当你在iframe页面关闭自身时
								var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
								parent.layer.close(index); //再执行关闭 
								}
							});
					},
					error : function(data) {
						alert("服务器繁忙");
					}
				})
				return false;
			});
		});
	</script>

</body>
</html>