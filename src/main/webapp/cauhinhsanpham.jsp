<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.entity.MauSac" %>
<%@ page import="org.example.entity.Size" %>
<%@ page import="org.example.entity.DanhMuc" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Cấu Hình Sản Phẩm</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.5.2/css/bootstrap.min.css">
  <style>
    .container {
      display: flex;
      flex-direction: column;
      align-items: center;
    }
    .table-container {
      display: flex;
      justify-content: space-between;
      width: 80%;
      margin-bottom: 40px;
    }
    table, th, td {
      border: 1px solid black;
    }
    .form-container {
      width: 300px; /* Chiều rộng cho form */
      margin-left: 20px; /* Khoảng cách giữa bảng và form */
    }
  </style>
</head>
<body>
<div class="container mt-5">
  <h2>Cấu Hình Sản Phẩm</h2>

  <!-- Bảng Màu Sắc -->
  <div class="table-container">
    <div>
      <h3>Màu Sắc</h3>
      <table class="table table-bordered">
        <thead>
        <tr>
          <th>ID</th>
          <th>Mã Màu</th>
          <th>Tên Màu</th>
          <th>Trạng Thái</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="mauSac" items="${mauSacList}">
          <tr onclick="selectMauSac(${mauSac.id}, '${mauSac.maMau}', '${mauSac.tenMau}', '${mauSac.trangThai}')">
            <td>${mauSac.id}</td>
            <td>${mauSac.maMau}</td>
            <td>${mauSac.tenMau}</td>
            <td>${mauSac.trangThai}</td>
            <td><a href="/sanpham/xoa?id=${product.id}" onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm này?');">Xóa</a></td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>

    <!-- Các Trường Nhập Liệu cho Màu Sắc -->
    <div class="form-container">
      <h4>Thêm/Sửa Màu Sắc</h4>
      <form action="${pageContext.request.contextPath}/mausac/sua" method="post" id="formMauSac">
        <input type="hidden" name="id" id="mauSacId">
        <div class="form-group">
          <label for="maMau">Mã Màu:</label>
          <input type="text" class="form-control" id="maMau" name="maMau" required>
        </div>
        <div class="form-group">
          <label for="tenMau">Tên Màu:</label>
          <input type="text" class="form-control" id="tenMau" name="tenMau" required>
        </div>
        <div class="form-group">
          <label for="trangThai">Trạng Thái:</label>
          <input type="text" class="form-control" id="trangThai" name="trangThai" required>
        </div>
        <button type="submit" class="btn btn-primary">Lưu</button>
      </form>
    </div>
  </div>

  <!-- Bảng Kích Thước -->
  <div class="table-container">
    <div>
      <h3>Kích Thước</h3>
      <table class="table table-bordered">
        <thead>
        <tr>
          <th>ID</th>
          <th>Mã Kích Thước</th>
          <th>Tên Kích Thước</th>
          <th>Trạng Thái</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="size" items="${sizeList}">
          <tr onclick="selectSize(${size.id}, '${size.maSize}', '${size.tenSize}', '${size.trangThai}')">
            <td>${size.id}</td>
            <td>${size.maSize}</td>
            <td>${size.tenSize}</td>
            <td>${size.trangThai}</td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>

    <!-- Các Trường Nhập Liệu cho Kích Thước -->
    <div class="form-container">
      <h4>Thêm/Sửa Kích Thước</h4>
      <form action="${pageContext.request.contextPath}/size/sua" method="post" id="formSize">
        <input type="hidden" name="id" id="sizeId">
        <div class="form-group">
          <label for="maSize">Mã Kích Thước:</label>
          <input type="text" class="form-control" id="maSize" name="maSize" required>
        </div>
        <div class="form-group">
          <label for="tenSize">Tên Kích Thước:</label>
          <input type="text" class="form-control" id="tenSize" name="tenSize" required>
        </div>
        <div class="form-group">
          <label for="trangThaiSize">Trạng Thái:</label>
          <input type="text" class="form-control" id="trangThaiSize" name="trangThai" required>
        </div>
        <button type="submit" class="btn btn-primary">Lưu</button>
      </form>
    </div>
  </div>

  <!-- Bảng Danh Mục -->
  <div class="table-container">
    <div>
      <h3>Danh Mục</h3>
      <table class="table table-bordered">
        <thead>
        <tr>
          <th>ID</th>
          <th>Mã Danh Mục</th>
          <th>Tên Danh Mục</th>
          <th>Trạng Thái</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="danhMuc" items="${danhMucList}">
          <tr onclick="selectDanhMuc(${danhMuc.id}, '${danhMuc.maDanhMuc}', '${danhMuc.tenDanhMuc}', '${danhMuc.trangThai}')">
            <td>${danhMuc.id}</td>
            <td>${danhMuc.maDanhMuc}</td>
            <td>${danhMuc.tenDanhMuc}</td>
            <td>${danhMuc.trangThai}</td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>

    <!-- Các Trường Nhập Liệu cho Danh Mục -->
    <div class="form-container">
      <h4>Thêm/Sửa Danh Mục</h4>
      <form action="${pageContext.request.contextPath}/danhmuc/sua" method="post" id="formDanhMuc">
        <input type="hidden" name="id" id="danhMucId">
        <div class="form-group">
          <label for="maDanhMuc">Mã Danh Mục:</label>
          <input type="text" class="form-control" id="maDanhMuc" name="maDanhMuc" required>
        </div>
        <div class="form-group">
          <label for="tenDanhMuc">Tên Danh Mục:</label>
          <input type="text" class="form-control" id="tenDanhMuc" name="tenDanhMuc" required>
        </div>
        <div class="form-group">
          <label for="trangThaiDanhMuc">Trạng Thái:</label>
          <input type="text" class="form-control" id="trangThaiDanhMuc" name="trangThai" required>
        </div>
        <button type="submit" class="btn btn-primary">Lưu</button>
      </form>
    </div>
  </div>

  <script>
    function selectMauSac(id, maMau, tenMau, trangThai) {
      document.getElementById('mauSacId').value = id;
      document.getElementById('maMau').value = maMau;
      document.getElementById('tenMau').value = tenMau;
      document.getElementById('trangThai').value = trangThai;
    }

    function selectSize(id, maSize, tenSize, trangThai) {
      document.getElementById('sizeId').value = id;
      document.getElementById('maSize').value = maSize;
      document.getElementById('tenSize').value = tenSize;
      document.getElementById('trangThaiSize').value = trangThai;
    }

    function selectDanhMuc(id, maDanhMuc, tenDanhMuc, trangThai) {
      document.getElementById('danhMucId').value = id;
      document.getElementById('maDanhMuc').value = maDanhMuc;
      document.getElementById('tenDanhMuc').value = tenDanhMuc;
      document.getElementById('trangThaiDanhMuc').value = trangThai;
    }
  </script>
</div>
</body>
</html>
