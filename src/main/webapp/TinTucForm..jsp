<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="insert" method="post">
		<label>Tiêu đề</label>
		<input name="tieuDe" />
		<label>Nội dung</label>
		<input name="noiDungTT" />
		<label>Liên kết</label>
		<input name="lienKet" />
		<select name= "danhMuc">
			<c:forEach var="listDanhMuc" items="${model}">
				<option  value="${listDanhMuc.maDM}">${listDanhMuc.tenDanhMuc}</option>
			</c:forEach>
		</select>
		<button type="submit">Thêm</button>
	</form>
	
</body>
</html>