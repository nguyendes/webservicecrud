package org.example.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Hdct {
    private int id;
    private int idHoaDon; // Khóa ngoại đến bảng Hóa Đơn
    private int idCtsp; // Khóa ngoại đến bảng Chi Tiết Sản Phẩm
    private int soLuongMua;
    private double giaBan;
    private double tongTien;
    private String trangThai;
    private Date ngayTao;
    private Date ngaySua;
}

