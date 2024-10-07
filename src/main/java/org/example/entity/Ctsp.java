package org.example.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Ctsp {
    private int id;
    private int idSp; // Khóa ngoại đến bảng Sản Phẩm
    private int idMauSac; // Khóa ngoại đến bảng Màu Sắc
    private int idSize; // Khóa ngoại đến bảng Size
    private double giaBan;
    private int soLuongTon;
    private String trangThai;
    private Date ngayTao;
    private Date ngaySua;
}

