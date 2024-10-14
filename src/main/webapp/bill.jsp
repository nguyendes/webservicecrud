<%--
  Created by IntelliJ IDEA.
  User: Tucuteprovjp
  Date: 10/8/2024
  Time: 10:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Hóa Đơn</title>
</head>
<body>
<h1>Danh Sách Hóa Đơn</h1>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>ID Khách Hàng</th>
        <th>Trạng Thái</th>
        <th>Ngày Tạo</th>
        <th>Ngày Sửa</th>
        <th>Địa Chỉ</th>
        <th>Số Điện Thoại</th>
        <th>Hành Động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="hoaDon" items="${hoaDons}">
        <tr>
            <td>${hoaDon.id}</td>
            <td>${hoaDon.idKhachHang}</td>
            <td>${hoaDon.trangThai}</td>
            <td>${hoaDon.ngayTao}</td>
            <td>${hoaDon.ngaySua}</td>
            <td>${hoaDon.diaChi}</td>
            <td>${hoaDon.soDienThoai}</td>
            <td>
                <a href="detail?id=${hoaDon.id}">Xem Chi Tiết</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<a href="/index">Quay Lại Trang Chính</a>
</body>
</html>

