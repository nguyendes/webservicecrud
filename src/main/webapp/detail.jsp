<%--
  Created by IntelliJ IDEA.
  User: Tucuteprovjp
  Date: 10/3/2024
  Time: 11:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Chi tiết sản phẩm</title>
</head>
<body>
<h2>Đây là trang chi tiết sản phẩm</h2>

<form action="/sanpham/chi-tiet?id=${CtspDetail.id}" method="post">
  Tên sản phẩm: <input type="text" name="tenSp" value="${CtspDetail.tenSp}" required> <br><br>
  Màu sắc: <input type="text" name="mauSac" value="${CtspDetail.mauSac}" required> <br><br>
  Kích cỡ: <input type="text" name="size" value="${CtspDetail.size}" required> <br><br>
  Giá bán: <input type="text" name="giaBan" value="${CtspDetail.giaBan}" required> <br><br>
  Số lượng tồn: <input type="text" name="soLuongTon" value="${CtspDetail.soLuongTon}" required> <br><br>
  Trạng thái: <input type="text" name="trangThai" value="${CtspDetail.trangThai}" required> <br><br>
  <button type="submit">Cập nhật</button>
</form>

</body>
</html>