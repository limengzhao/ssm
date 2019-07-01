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
<title>添加角色</title>
<link rel="stylesheet" href="layui/css/layui.css">
<script type="text/javascript" src="layui/layui.js"></script>
<style type="text/css">
.layui-col {
	text-align: center;
}

.layui-form-label {
	width: 100px;
}

.layui-input-block {
	margin-left: 130px;
	margin-top: 15px;
}
</style>
</head>
<body>
	<div class="layui-container">
	  <div class="layui-row">
			<h1 style="text-align: center; margin-top: 30px;">${title}</h1>
	  </div>
	  <div class="layui-row layui-col-space10" style="margin-top: 10px;">
	  	<div class="layui-col-md12 layui-col">
	  		<form class="layui-form" method="post">
	  		<input type="hidden" name="roleId" value="${role.roleId==null?'':role.roleId}"/>
	  		<fieldset class="layui-elem-field layui-field-title">
	  			<legend>${title}</legend>
	  			<div class="layui-form-item">
						<label class="layui-form-label">角色名称</label>
					<div class="layui-input-block">
						<input type="text" name="roleName" value="${role.roleName==null?'':role.roleName}" required lay-verify="required"
						placeholder="请输入角色名称" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
						<label class="layui-form-label">角色描述</label>
					<div class="layui-input-block">
						<input type="text" name="roleDescription" value="${role.roleDescription==null?'':role.roleDescription}" required lay-verify="required"
						 autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">角色状态</label>
					<div class="layui-input-block layui-form" lay-filter="selectFilter">
						<select id="select" name="roleStatus" lay-verify="required">
								<option value="">请选择</option>
								<option value="0">已启用</option>
								<option value="1">未启用</option>
						</select>
					</div>
			   </div>
			   <div class="layui-form-item" style="margin-top: 50px;">
					<button class="layui-btn" lay-submit lay-filter="saveOrUpdate">${menu.menuid==null?'保存':'更新'}</button>
						     <!-- <button type="reset" class="layui-btn layui-btn-primary" >重置</button> -->
					<button type="button" id="cancel"  class="layui-btn layui-btn-normal">取消</button>
				</div>
	  		</fieldset>
	  		</form>
	  	</div>
	  </div>	
	</div>

	<script type="text/javascript">
		layui.use([ 'layer', 'jquery', 'form' ], function() {
			var layer = layui.layer;
			var form = layui.form;
			var $ = layui.$;
			//获取model的值在js中使用
			var roleStatus='${role.roleStatus}';
			$('#select').val(roleStatus);
			form.render('select','selectFilter'); 
			//获取model的值在js中使用
			$('#cancel').on('click',function(data){
				//当你在iframe页面关闭自身时
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭 
			});
			form.on('submit(saveOrUpdate)',function(data){
				var roleId='${role.roleId}';
				if(roleId!=null&&roleId!=''){
					//更新角色
					$.ajax({
						url:'<%=basePath%>updateRole',
						method:'post',
						data:data.field,
						success:function(res){
							layer.open({
								type:0,
								content:'更新成功！',
								btn:['确定'],
								yes:function(){
									//当你在iframe页面关闭自身时
									window.parent.location.reload();//刷新父页面方法
									var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
									parent.layer.close(index); //再执行关闭 
								}
							});
						},
						error:function(res){
							alert('服务器繁忙！');
						}
					});
					return false;
				}else{
					//保存角色
					$.ajax({
						url:'<%=basePath%>saveRole',
						method:'post',
						data:data.field,
						success:function(res){
							layer.open({
								type:0,
								content:'保存成功！',
								btn:['确定'],
								yes:function(){
									//当你在iframe页面关闭自身时
									window.parent.location.reload();//刷新父页面方法
									var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
									parent.layer.close(index); //再执行关闭 
								}
							});
						},
						error:function(data){
							alert('服务器繁忙');
						}
					});
					return false;	
					
				}	
			});
		});
	</script>
</body>
</html>