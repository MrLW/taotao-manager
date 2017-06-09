<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>分页查询所有员工</title>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <link rel="stylesheet" href="themes/default/easyui.css" type="text/css"></link>
    <link rel="stylesheet" href="themes/icon.css" type="text/css"></link>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
    
    </head>
  <body>
  		<table id="dg" style="width: 800px;"></table>
  		
  		<script type="text/javascript">
  			$("#dg").datagrid({
  				
  				url:'${pageContext.request.contextPath}/servlet/EmpServlet?time='+new Date().getTime(),
  				columns:[[
  							{field:'empno',title:'编号',width:100},    
					        {field:'ename',title:'姓名',width:100},    
					        {field:'job',title:'工作',width:100},    
					        {field:'mgr',title:'上编',width:100},    
					        {field:'hiredate',title:'入时',width:100},    
					        {field:'sal',title:'月薪',width:100},    
					        {field:'comm',title:'佣金',width:100},    
					        {field:'deptno',title:'部编',width:100} 
  						]],
  				fitColumns : true,
			    singleSelect : true,
			    pagination : true,
			    pageNumber : 1,
			    pageSize : 3,
			    pageList : [3,4,5],
			    fit:true
  			});
  		</script>
  		
  </body>
</html>
