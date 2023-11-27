<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="update?id=${model.maTT}" method="post">
		<label>Tiêu đề</label>
		<input name="tieuDe" value="${model.tieuDe}"/>
		<label>Nội dung</label>
		<input name="noiDungTT" value="${model.noiDungTT }"/>
		<label>Liên kết</label>
		<input name="lienKet" value="${model.lienKet}"/>
		<select name= "danhMuc">
			<option>${model.getMaDM().getTenDanhMuc()}</option>
			<c:forEach var="listDanhMuc" items="${models}">
				<option  value="${listDanhMuc.maDM}">${listDanhMuc.tenDanhMuc}</option>
			</c:forEach>
		</select>
		<button type="submit">Sửa</button>
	</form>
	
</body>
</html>