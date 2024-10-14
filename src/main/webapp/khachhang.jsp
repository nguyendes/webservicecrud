<%--
  Created by IntelliJ IDEA.
  User: Tucuteprovjp
  Date: 10/8/2024
  Time: 11:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Danh Sách Khách Hàng</title>
    <script type="text/javascript">
        function showCustomerDetails(id, hoTen, diaChi, sdt, trangThai, ngayTao, ngaySua) {
            document.getElementById("id").value = id;
            document.getElementById("hoTen").value = hoTen;
            document.getElementById("diaChi").value = diaChi;
            document.getElementById("sdt").value = sdt;
            document.getElementById("trangThai").value = trangThai;
            document.getElementById("ngayTao").value = ngayTao;
            document.getElementById("ngaySua").value = ngaySua;

            // Chuyển chế độ form sang "Cập Nhật"
            document.getElementById("action").value = "capnhat";
            document.getElementById("submitButton").value = "Cập Nhật";
        }

        function resetForm() {
            document.getElementById("id").value = "";
            document.getElementById("hoTen").value = "";
            document.getElementById("diaChi").value = "";
            document.getElementById("sdt").value = "";
            document.getElementById("trangThai").value = "";
            document.getElementById("ngayTao").value = "";
            document.getElementById("ngaySua").value = "";

            // Chuyển chế độ form sang "Thêm"
            document.getElementById("action").value = "them";
            document.getElementById("submitButton").value = "Thêm";
        }
    </script>
</head>
<body>

<h2>Danh Sách Khách Hàng</h2>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Họ Tên</th>
        <th>Địa Chỉ</th>
        <th>Số Điện Thoại</th>
        <th>Trạng Thái</th>
        <th>Ngày Tạo</th>
        <th>Ngày Sửa</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="khachHang" items="${khachHangList}">
        <tr onclick="showCustomerDetails('${khachHang.id}', '${khachHang.hoTen}', '${khachHang.diaChi}', '${khachHang.sdt}', '${khachHang.trangThai}', '${khachHang.ngayTao}', '${khachHang.ngaySua}')">
            <td>${khachHang.id}</td>
            <td>${khachHang.hoTen}</td>
            <td>${khachHang.diaChi}</td>
            <td>${khachHang.sdt}</td>
            <td>${khachHang.trangThai}</td>
            <td>${khachHang.ngayTao}</td>
            <td>${khachHang.ngaySua}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h2>Thông Tin Khách Hàng</h2>

<form action="/khachhang/them" method="POST">
    <input type="hidden" id="action" name="action" value="them">

    <label for="id">ID:</label>
    <input type="text" id="id" name="id" readonly><br><br>

    <label for="hoTen">Họ Tên:</label>
    <input type="text" id="hoTen" name="hoTen"><br><br>

    <label for="diaChi">Địa Chỉ:</label>
    <input type="text" id="diaChi" name="diaChi"><br><br>

    <label for="sdt">Số Điện Thoại:</label>
    <input type="text" id="sdt" name="sdt"><br><br>

    <label for="trangThai">Trạng Thái:</label>
    <input type="text" id="trangThai" name="trangThai"><br><br>

    <label for="ngayTao">Ngày Tạo:</label>
    <input type="text" id="ngayTao" name="ngayTao" readonly><br><br>

    <label for="ngaySua">Ngày Sửa:</label>
    <input type="text" id="ngaySua" name="ngaySua" readonly><br><br>

    <input type="submit" id="submitButton" value="Thêm">
    <button type="button" onclick="resetForm()">Reset</button>
</form>

<a href="/index">Quay Lại Trang Chính</a>
</body>
</html>
