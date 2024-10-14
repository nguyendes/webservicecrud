<%--
  Created by IntelliJ IDEA.
  User: Tucuteprovjp
  Date: 10/8/2024
  Time: 5:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chi Tiết Hóa Đơn</title>
</head>
<body>

<h2>Chi Tiết Hóa Đơn</h2>

<c:if test="${not empty hdcts}">
    <table border="1">
        <thead>
        <tr>
            <th>ID</th>
            <th>Sản phẩm</th>
            <th>Số lượng</th>
            <th>Giá bán</th>
            <th>Tổng tiền</th>
            <th>Ngày tạo</th>
            <th>Ngày sửa</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="tongGiaTri" value="0" />
        <c:forEach var="hdct" items="${hdcts}">
            <tr>
                <td>${hdct.id}</td>
                <td>${hdct.idCtsp}</td>
                <td>${hdct.soLuongMua}</td>
                <td>${hdct.giaBan}</td>
                <td>${hdct.tongTien}</td>
                <td>${hdct.ngayTao}</td>
                <td>${hdct.ngaySua}</td>
            </tr>
            <c:set var="tongGiaTri" value="${tongGiaTri + hdct.tongTien}" />
        </c:forEach>
        </tbody>
    </table>

    <h3>Tổng giá trị hóa đơn: ${tongGiaTri} VND</h3>
</c:if>

</body>
</html>
