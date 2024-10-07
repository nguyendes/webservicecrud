package org.example.entity;

import lombok.Data;
import java.util.Date;

@Data
public class HoaDon {
    private int id;
    private int idKhachHang; // Khóa ngoại đến bảng Khách Hàng
    private String trangThai;
    private Date ngayTao;
    private Date ngaySua;
    private String diaChi;
    private String soDienThoai;
}
