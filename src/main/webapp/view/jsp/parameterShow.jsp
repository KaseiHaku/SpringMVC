<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Parameter Show</title>
</head>
<body>
	<h1>Client 向 Server 传递数据结果展示</h1>
	
	<h3>bind1: spring mvc 默认绑定的类型</h3>
	<b><%=request.getAttribute("bind1") %></b><hr/>
	
	<h3>bind2</h3>
	<b>${requestScope.bind2}</b><hr/>
	
	<h3>bind3</h3>
	<b>${requestScope.bind3}</b><hr/>
	
	<h3>bind4</h3>
	<b>${requestScope.bind4}</b><hr/>
	
	<h3>bind5</h3>
	<b>${requestScope.bind5}</b><hr/>
	
	<h3>bind6</h3>
	<b>${requestScope.bind6}</b><hr/>
	
	<h3>bind7</h3>
	<b>${requestScope.bind7}</b><hr/>
	
	<h3>bind11</h3>
	<b>${requestScope.bind11}</b><hr/>
	
	<!-- ################################################################# -->	
	<h1>Controller 向 jsp 页面传递数据展示</h1>
	<h3>data2jsp1</h3>
	<b>${requestScope.data2jsp1}</b><hr/>
	
	<h3>data2jsp2</h3>
	<b>${requestScope.data2jsp2}</b><hr/>
	
	<h3>data2jsp3</h3>
	<b>${requestScope.data2jsp3}</b><hr/>
	
	<h3>data2jsp4</h3>
	<b>${requestScope.data2jsp4}</b><hr/>
	
	
	
</body>
</html>