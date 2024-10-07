package org.example.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Size {
    private int id;
    private String maSize;
    private String tenSize;
    private String trangThai;
    private Date ngayTao;
    private Date ngaySua;
}

