package org.example.entity;

import lombok.Data;
import java.util.Date;

@Data
public class MauSac {
    private int id;
    private String maMau;
    private String tenMau;
    private String trangThai;
    private Date ngayTao;
    private Date ngaySua;
}

