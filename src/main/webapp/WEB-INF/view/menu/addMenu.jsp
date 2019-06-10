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
				<input type="hidden" name="menuid" value="${menu.menuid==null?'':menu.menuid}"/>
					<fieldset class="layui-elem-field layui-field-title">
						<legend>${title}</legend>
						<div class="layui-form-item">
							<label class="layui-form-label">菜单名称</label>
							<div class="layui-input-block">
								<input type="text" name="menuName" value="${menu.menuName==null?'':menu.menuName}" required lay-verify="required"
									placeholder="请输入菜单名称" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">父级菜单</label>
							<div class="layui-input-block">
								<select id="parentMenu" name="parentMenuId" lay-verify="required" lay-filter="menuName">
									<option value="请选择父级菜单"></option>
								</select>
							</div>
						</div>
						<!-- <div class="layui-form-item">
							<label class="layui-form-label">父级菜单</label>
							<div class="layui-input-block">
								<input type="text" name="title" required lay-verify="required"
									placeholder="请输入菜单名称" autocomplete="off" class="layui-input">
							</div>
						</div> -->
						<div class="layui-form-item">
							<label class="layui-form-label">菜单URL</label>
							<div class="layui-input-block">
								<input type="text" name="menuUrl" value="${menu.menuUrl==null?'':menu.menuUrl}" required lay-verify="required"
									placeholder="菜单URL" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">状态</label>
							<div class="layui-input-block layui-form"
								lay-filter="selectFilter">
								<select id="select" name="status" lay-verify="required">
									<option value="">请选择</option>
									<option value="0">已启用</option>
									<option value="1">未启用</option>
								</select>
							</div>
						</div>
						<div class="layui-form-item layui-form-text">
							<label class="layui-form-label">备注</label>
							<div class="layui-input-block">
								<textarea name="remark" placeholder="请输入内容" class="layui-textarea">${menu.remark==null?'':menu.remark}</textarea>
							</div>
						</div>
						<div class="layui-form-item">
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
			//动态获取select option 
			$.ajax({
				url:'<%=basePath%>getParentMenu',
				method:'GET',
				data:'null',
				success:function(data){
					$.each(data,function(index,item){
						$('#parentMenu').append(new Option(item.menuName, item.menuid));// 下拉菜单里添加元素
					});
					layui.form.render("select");
				},
				error:function(data){
					console.log(data);
				}
			});
			$('#cancel').on('click',function(data){
				//当你在iframe页面关闭自身时
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭 
			});
			
			form.on('submit(saveOrUpdate)',function(data){
				var menuid='${menu.menuid}';
				if(menuid!=null&&menuid!=""){
					$.ajax({
						url:'<%=basePath%>updateMenu',
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
					//var jsonuserinfo = JSON.stringify($('.layui-form').serializeObject());  
					$.ajax({
						url:'<%=basePath%>saveMenu',
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