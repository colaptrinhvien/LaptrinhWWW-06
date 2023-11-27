<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="margin-left: 400px"> 
		<img alt="" src='<c:url value="/img/anh.png" />'>
	</div>
	<div style="margin-left: 700px">
		<a href='<c:url value = "/DanhSachTinTucServlet"/>'>Danh sách tin tức</a>
		<a>|</a>
		<a>Thêm tin tức</a>
	</div>
</body>
</html>