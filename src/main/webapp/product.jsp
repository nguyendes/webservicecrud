<%--
  Created by IntelliJ IDEA.
  User: Tucuteprovjp
  Date: 10/2/2024
  Time: 5:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Danh Sách Sản Phẩm</title>
    <script type="text/javascript">
        function showProductDetails(id, maSanPham, tenSanPham, trangThai, ngayTao, ngaySua, idDanhMuc) {
            document.getElementById("id").value = id;
            document.getElementById("maSanPham").value = maSanPham;
            document.getElementById("tenSanPham").value = tenSanPham;
            document.getElementById("trangThai").value = trangThai;
            document.getElementById("ngayTao").value = ngayTao;
            document.getElementById("ngaySua").value = ngaySua;
            document.getElementById("idDanhMuc").value = idDanhMuc;

            // Chuyển chế độ form sang "Cập Nhật"
            document.getElementById("action").value = "capnhat";
            document.getElementById("submitButton").value = "Cập Nhật";
        }

        function resetForm() {
            document.getElementById("id").value = "";
            document.getElementById("maSanPham").value = "";
            document.getElementById("tenSanPham").value = "";
            document.getElementById("trangThai").value = "";
            document.getElementById("ngayTao").value = "";
            document.getElementById("ngaySua").value = "";
            document.getElementById("idDanhMuc").value = "";

            // Chuyển chế độ form sang "Thêm"
            document.getElementById("action").value = "them";
            document.getElementById("submitButton").value = "Thêm";
        }
    </script>
</head>
<body>

<h2>Danh Sách Sản Phẩm</h2>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Mã Sản Phẩm</th>
        <th>Tên Sản Phẩm</th>
        <th>Trạng Thái</th>
        <th>Ngày Tạo</th>
        <th>Ngày Sửa</th>
        <th>Danh Mục</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${listProduct}">
        <tr onclick="showProductDetails('${product.id}', '${product.maSanPham}', '${product.tenSanPham}', '${product.trangThai}', '${product.ngayTao}', '${product.ngaySua}', '${product.idDanhMuc}')">
            <td>${product.id}</td>
            <td>${product.maSanPham}</td>
            <td>${product.tenSanPham}</td>
            <td>${product.trangThai}</td>
            <td>${product.ngayTao}</td>
            <td>${product.ngaySua}</td>
            <td>${product.idDanhMuc}</td>
            <td><a href="/sanpham/chi-tiet?id=${product.id}">Chi tiet</a></td>
            <td><a href="/sanpham/xoa?id=${product.id}" onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm này?');">Xóa</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h2>Thông Tin Sản Phẩm</h2>

<form action="/sanpham/them" method="POST">
    <input type="hidden" id="action" name="action" value="them">

    <label for="id">ID:</label>
    <input type="text" id="id" name="id" readonly><br><br>

    <label for="maSanPham">Mã Sản Phẩm:</label>
    <input type="text" id="maSanPham" name="maSanPham"><br><br>

    <label for="tenSanPham">Tên Sản Phẩm:</label>
    <input type="text" id="tenSanPham" name="tenSanPham"><br><br>

    <label for="trangThai">Trạng Thái:</label>
    <input type="text" id="trangThai" name="trangThai"><br><br>

    <label for="ngayTao">Ngày Tạo:</label>
    <input type="text" id="ngayTao" name="ngayTao" readonly><br><br>

    <label for="ngaySua">Ngày Sửa:</label>
    <input type="text" id="ngaySua" name="ngaySua" readonly><br><br>

    <label for="idDanhMuc">Danh Mục:</label>
    <input type="text" id="idDanhMuc" name="idDanhMuc"><br><br>

    <input type="submit" id="submitButton" value="Thêm">
    <button type="button" onclick="resetForm()">Reset</button>
</form>

</body>
</html>
