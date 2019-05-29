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
<title>主页面</title>
<link rel="stylesheet" href="layui/css/layui.css">
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">layui 后台布局</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item"><a href="">控制台</a></li>
				<li class="layui-nav-item"><a href="">商品管理</a></li>
				<li class="layui-nav-item"><a href="">用户</a></li>
				<li class="layui-nav-item"><a href="javascript:;">其它系统</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">邮件管理</a>
						</dd>
						<dd>
							<a href="">消息管理</a>
						</dd>
						<dd>
							<a href="">授权管理</a>
						</dd>
					</dl></li>
			</ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;"> <img
						src="http://t.cn/RCzsdCq" class="layui-nav-img">
						${user.username}
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">基本资料</a>
						</dd>
						<dd>
							<a href="">安全设置</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="<%=basePath%>loginOut">退出</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="left-menu">
					<li class="layui-nav-item">
					<a class="" href="javascript:;"><i class="layui-icon" style="margin-right: 5px">&#xe620;</i>系统管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;" data_id="1" data_title="用户管理"
									data-url="<%=basePath%>findUserPage" class="site_active" data_type="tabAdd"> 用户管理</a>
							</dd>
							<dd>
								<a href="javascript:;" data_id="2" data_title="菜单管理"
									data-url="user.jsp" class="site_active" data_type="tabAdd">菜单管理</a>
							</dd>
							<dd>
								<a href="javascript:;">角色管理</a>
							</dd>
							<dd>
								<a href="">超链接</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;">解决方案</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;">列表一</a>
							</dd>
							<dd>
								<a href="javascript:;">列表二</a>
							</dd>
							<dd>
								<a href="">超链接</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="">云市场</a></li>
					<li class="layui-nav-item"><a href="">发布商品</a></li>
				</ul>
			</div>
		</div>

		<div class="layui-body" style="bottom: 0px;">
			<!-- 内容主体区域 -->
			<div class="layui-tab" lay-filter="tabs" style="padding: 15px;" lay-allowclose="true">
				<ul class="layui-tab-title">
					<!-- <li class="layui-this">首页</li> -->
				</ul>
				<div class="layui-tab-content">
					<!-- <div class="layui-tab-item layui-show">首页内容...</div> -->
				</div>
			</div>
		</div>

		<div class="layui-footer">
			<!-- 底部固定区域 -->
			© layui.com - 底部固定区域
		</div>
	</div>
	<script type="text/javascript" src="layui/layui.js"></script>
	<script>
		//JavaScript代码区域
		layui.use(['element','layer','jquery'], function() {
			var element = layui.element;
			var $=layui.$;
			$('.site_active').on('click',function(){
				var obj=$(this);
				if($('.layui-tab-title li[lay-id]').length<=0){
					active.tabAdd(obj.attr("data-url"),obj.attr("data_id"),obj.attr("data_title"));	
				}else{
					//否则判断该tab项是否以及存在
					var flag=false;
					$.each($(".layui-tab-title li[lay-id]"),function(){
						if($(this).attr('lay-id')==obj.attr("data_id")){
							flag=true;
						}
					});
					if(flag==false){
						//标志为false 新增一个tab项
						active.tabAdd(obj.attr("data-url"),obj.attr("data_id"),obj.attr("data_title"));	
					}
				}
				active.tabChange(obj.attr("data_id"));
			});
				
			//绑定事件集
			var active={
					tabAdd:function(url,id,name){
						element.tabAdd('tabs',{
							title:name,
							content:'<iframe data-frameid="' + id + '" scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:99%;"></iframe>',
							id:id
						});
						FrameWH();  //计算ifram层的大小
					},
					tabChange:function(id){
						element.tabChange('tabs',id);
					},
					tabDelete:function(id){
						element.tabDelete('tabs',id);
					}
			}			
			//计算framWH
			function FrameWH(){
				var h = $(window).height();
	            $("iframe").css("height",h+"px");
			}
			

		});
	</script>
</body>
</html>
</html>