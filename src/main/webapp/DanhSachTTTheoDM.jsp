<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1"> 
		<tr>
			<th>Tiêu Đề</th>
			<th style="width: 150px">Nội dung tin tức</th>
			<th style="width: 150px">Liên kết</th>
			<th style="width: 150px">Tên danh mục</th>
			<th style="width: 150px">Người quản lý</th>
			<th>Chỉnh sửa</th>
			
		<tr>
		<c:forEach var="listTT" items="${model}">
		<tr>
			
				<td>${listTT.tieuDe}</td>
				<td>${listTT.noiDungTT}</td>
				<td>${listTT.lienKet}</td>
				<td>${listTT.getMaDM().getTenDanhMuc()}</td>
				<td>${listTT.getMaDM().getNguoiQuanLy()}</td>
				<td>
				<form action="delete?id=${listTT.maTT}" method="post">
					<button type="submit" >Xóa</button>
				</form>
				</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>