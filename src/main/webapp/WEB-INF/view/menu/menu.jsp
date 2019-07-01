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
<title>菜单管理</title>
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
		<table id="menuTable" class="layui-table" lay-size="sm" lay-filter="table"></table>
		<script type="text/html" id="menuToolbar">
  			<div class="layui-btn-container">
    			<button class="layui-btn" lay-event="addMenu">新     增</button>
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
			    elem: '#menuTable'
			    ,id:'menuTableReload'
			    ,height:540
			    ,width:1000
			    ,title:'菜单管理'
			    ,limit:10
			    ,url: '<%=basePath%>findPageMenu'
			    ,toolbar: '#menuToolbar'
			    ,page: true //开启分页
			    ,cols: [[ //表头
			      {field: 'menuid', title: 'ID', width:80, sort: true}
			      ,{field: 'menuName', title: '菜单名称', width:100}
			      ,{field: 'parentMenu', title: '父级菜单名称', width:100}
			      ,{field: 'menuUrl', title: '菜单URL', width:80} 
			      ,{field: 'status', title: '状态', width:100,templet:'#viewStatus'}
			      ,{field: 'remark', title: '备注', width:100}
			      ,{title: '操作', width:150, align:'center', toolbar: '#barOperator'}
			    ]]
			    ,done: function(res, curr, count){
			        //如果是异步请求数据方式，res即为你接口返回的信息。
			        //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
			        console.log(res);
			        
			        //得到当前页码
			        console.log(curr); 
			        
			        //得到数据总量
			        console.log(count);
			      }
			  });
			//监听按钮事件
			table.on('toolbar(table)',function(obj){
				if(obj.event=="addMenu"){
					layer.open({
						type:2,
						title:'添加菜单',
						content:'<%=basePath%>addMenuPage',
						area:["80%","80%"]
					});
				}
			});
			//表格重载
			active = {
					reload: function(){
					      var demoReload = $('#demoReload');
					      //执行重载
					      table.reload('menuTableReload', {
					        page: {
					          curr: 1 //重新从第 1 页开始
					        }
					        ,where: {
					        	menuid: demoReload.val()
					        }
					      });
					    },
				  };
			$('.demoTable .layui-btn').on('click', function(){
			    var type = $(this).data('type');
			    active[type] ? active[type].call(this) : '';
			  });
			
			//监听工具
			  table.on('tool(table)',function(obj){
				  var data = obj.data;
				  var menuid={
							 "menuid":data.menuid
					  }
				  if(obj.event=="updateMenu"){//查看这条数据
					  layer.open({
						  type:2,
						  title:'更新菜单',
						  offset: '30px',
						  content:'<%=basePath%>updateMenuPage?menuid='+data.menuid,
						  area:["80%","80%"]
					  });
				  
				  }else if(obj.event=="delMenu"){
					  layer.confirm('确认删除？',function(index){
						  obj.del();
						  layer.close(index);
						  $.ajax({
							  	url:'<%=basePath%>deleteMenu',
								data:menuid,
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
       <a class="layui-btn layui-btn-xs" lay-event="updateMenu">更新</a>
       <a  class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delMenu">删除</a>
    </script>
    <script type="text/html" id="viewStatus">
		{{# if (d.status=== '0') { }}   
  		启用
		{{# } else if(d.status=== '1') { }}  
  		禁用
		{{# } else { }}  
  		未知
		{{# } }}
	</script>

</body>
</html>