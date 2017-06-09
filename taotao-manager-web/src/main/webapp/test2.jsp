<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css"
	href="js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="js/jquery-easyui-1.4.1/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="css/taotao.css" />
<script type="text/javascript"
	src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript"
	src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/common.js"></script>



<body>
	<table class="easyui-datagrid" id="itemList" title="商品列表"
		data-options="singleSelect:false,collapsible:true,pagination:true,url:'/item/list',method:'get',pageSize:10,pageNumber:1,toolbar:toolbar">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id',width:60">商品ID</th>
				<th data-options="field:'title',width:200">商品标题</th>
				<th data-options="field:'cid',width:100">叶子类目</th>
				<th data-options="field:'sellPoint',width:100">卖点</th>
				<th
					data-options="field:'price',width:70,align:'right',formatter:TAOTAO.formatPrice">价格</th>
				<th data-options="field:'num',width:70,align:'right'">库存数量</th>
				<th data-options="field:'barcode',width:100">条形码</th>
				<th
					data-options="field:'status',width:60,align:'center',formatter:TAOTAO.formatItemStatus">状态</th>
				<th
					data-options="field:'created',width:130,align:'center',formatter:TAOTAO.formatDateTime">创建日期</th>
				<th
					data-options="field:'updated',width:130,align:'center',formatter:TAOTAO.formatDateTime">更新日期</th>
			</tr>
		</thead>
	</table>
</body>
</html>