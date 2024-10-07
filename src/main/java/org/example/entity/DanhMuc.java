package org.example.entity;

import lombok.Data;
import java.util.Date;

@Data
public class DanhMuc {
    private int id;
    private String maDanhMuc;
    private String tenDanhMuc;
    private String trangThai;
    private Date ngayTao;
    private Date ngaySua;
}

