<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<title>Insert title here</title>
</head>
	<body>
	<table width="80%" align="center">
		<tr>
			<td colspan="7" align="right"><a href="index">返回主页</a></td>
		</tr>


		<tr>
			<td>股票代码</td>
			<td>开盘价</td>
			<td>收盘价</td>
			<td>最高价</td>
			<td>最低价</td>
		</tr>

		<c:forEach items="${list}" var="stock">
			<tr>
				<td>${stock.code}</td>
				<td>${stock.open}</td>
				<td>${stock.close}</td>
				<td>${stock.high}</td>
				<td>${stock.low}</td>
			</tr>
		</c:forEach>
	</table>


	<c:if test="${empty list}">
		<tr align="center">没有查询到该股票</tr>

	</c:if>
	</body>
</html>