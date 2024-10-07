package org.example.entity;

import lombok.Data;
import java.util.Date;

@Data
public class KhachHang {
    private int id;
    private String hoTen;
    private String diaChi;
    private String sdt;
    private String trangThai;
    private Date ngayTao;
    private Date ngaySua;
}

