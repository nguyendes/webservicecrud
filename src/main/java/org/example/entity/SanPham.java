package org.example.entity;

import lombok.Data;
import java.util.Date;

@Data
public class SanPham {
    private int id;
    private String maSanPham;
    private String tenSanPham;
    private String trangThai;
    private Date ngayTao;
    private Date ngaySua;
    private int idDanhMuc; // Khóa ngoại đến bảng Danh Muc
}

