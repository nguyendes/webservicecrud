<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tạo Hóa Đơn</title>
</head>
<body>
<h1>Tạo Hóa Đơn</h1>
<script>
    function calculateTotal(element) {
        const giaBan = parseFloat(element.getAttribute('data-gia-ban'));
        const soLuongMua = parseInt(element.value);

        // Tính tổng tiền
        const tongTien = giaBan * soLuongMua;

        // Cập nhật tổng tiền vào ô hiển thị và trường ẩn
        const tongTienCell = element.closest('tr').querySelector('.tongTienSanPham');
        const tongTienHidden = element.closest('tr').querySelector('.tongTienSanPhamHidden');
        tongTienCell.textContent = tongTien;
        tongTienHidden.value = tongTien;

        updateTotalInvoiceValue();
    }


    function updateTotalInvoiceValue() {
        let totalValue = 0;

        // Lặp qua tất cả các ô tổng tiền sản phẩm và tính tổng giá trị hóa đơn
        document.querySelectorAll('.tongTienSanPham').forEach(function(cell) {
            totalValue += parseFloat(cell.textContent.replace(/,/g, '')) || 0; // Loại bỏ dấu phẩy khi tính toán
        });

        // Cập nhật tổng giá trị hóa đơn
        document.getElementById('tongGiaTriHoaDon').textContent = totalValue.toLocaleString('vi-VN');
    }
</script>

<!-- Form tìm kiếm khách hàng -->
<form action="/hoadon/tim-khach" method="post">
    <label for="soDienThoai">Số Điện Thoại:</label>
    <input type="text" id="soDienThoai" name="soDienThoai" required>
    <button type="submit" name="action" value="tim-khach">Tìm Khách Hàng</button>
</form>

<!-- Hiển thị thông tin khách hàng nếu tìm thấy -->
<c:if test="${not empty khachHang}">
    <h2>Thông Tin Khách Hàng</h2>
    <p>Tên: ${khachHang.hoTen}</p>
    <p>Địa Chỉ: ${khachHang.diaChi}</p>
    <p>Số Điện Thoại: ${khachHang.sdt}</p>

    <!-- Nút tạo hóa đơn mới -->
    <form action="/hoadon/them" method="post">
        <input type="hidden" name="soDienThoai" value="${khachHang.sdt}" />
        <input type="hidden" name="trangThai" value="Đang Xử Lý"/> <!-- Trạng thái tự động là Đang Xử Lý -->
        <label for="diaChi">Địa Chỉ:</label>
        <input type="text" id="diaChi" name="diaChi" value="${khachHang.diaChi}" required><br>

        <button type="submit" name="action" value="them">Tạo Hóa Đơn</button>
    </form>
</c:if>

<!-- Hiển thị danh sách hóa đơn đang xử lý -->
<c:if test="${not empty pendingInvoices}">
    <h2>Hóa Đơn Đang Xử Lý</h2>
    <table border="1">
        <thead>
        <tr>
            <th>ID Hóa Đơn</th>
            <th>Khách Hàng</th>
            <th>Ngày Tạo</th>
            <th>Trạng Thái</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="invoice" items="${pendingInvoices}">
            <tr>
                <td><a href="/hoadon/hien-thi?idHoaDon=${invoice.id}">${invoice.id}</a></td>
                <td>${invoice.idKhachHang}</td>
                <td>${invoice.ngayTao}</td>
                <td>${invoice.trangThai}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<!-- Hiển thị danh sách sản phẩm để chọn -->
<c:if test="${not empty ctspDetails}">
    <h2>Danh Sách Sản Phẩm</h2>
    <form action="/hoadon/thanh-toan" method="post" id="productForm">
        <input type="hidden" name="id" value="${pendingInvoices[0].id}"/>
        <table border="1">
            <thead>
            <tr>
                <th>ID</th>
                <th>Mã Sản Phẩm</th>
                <th>Tên Sản Phẩm</th>
                <th>Màu Sắc</th>
                <th>Size</th>
                <th>Giá Bán</th>
                <th>Số Lượng Còn</th>
                <th>Số Lượng Mua</th>
                <th>Tổng Tiền</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="ctspDetails" items="${ctspDetails}" varStatus="status">
                <tr>
                    <td>${ctspDetails.id}</td>
                    <td>${ctspDetails.maSanPham}</td>
                    <td>${ctspDetails.tenSp}</td>
                    <td>${ctspDetails.mauSac}</td>
                    <td>${ctspDetails.size}</td>
                    <td>${ctspDetails.giaBan}</td>
                    <td>${ctspDetails.soLuongTon}</td>
                    <!-- Add hidden field for idctsp -->
                    <input type="hidden" name="idSp${status.index}" value="${ctspDetails.id}">
                    <input type="hidden" name="giaBan${status.index}" value="${ctspDetails.giaBan}">
                    <input type="hidden" class="tongTienSanPhamHidden" name="tongTien${status.index}" value="0">
                    <td>
                        <input type="number" name="soLuongMua${status.index}" value="0" min="0" max="${ctspDetails.soLuongTon}" required
                               class="soLuongMua"
                               data-gia-ban="${ctspDetails.giaBan}"
                               oninput="calculateTotal(this)">
                    </td>
                    <td class="tongTienSanPham">0</td> <!-- Tổng tiền của sản phẩm -->
                </tr>
            </c:forEach>

            </tbody>
        </table>
        <p><strong>Tổng Giá Trị Hóa Đơn: </strong><span id="tongGiaTriHoaDon">0</span></p> <!-- Tổng giá trị hóa đơn -->
        <button type="submit" name="action" value="thanh-toan" onclick="return confirm('Ban co mun tt ko?')">Thanh Toán</button>
    </form>
</c:if>


</body>
</html>