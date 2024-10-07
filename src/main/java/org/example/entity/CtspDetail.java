package org.example.entity;

import java.util.Date;

public class CtspDetail extends Ctsp {
    private String maSanPham;
    private String tenSp;
    private String mauSac;
    private String size;

    public CtspDetail(int idSp,String maSanPham, String tenSp, String mauSac, String size, double giaBan, int soLuongTon, String trangThai, Date ngayTao, Date ngaySua) {
        super();
        this.setIdSp(idSp);
        this.maSanPham = maSanPham;
        this.tenSp = tenSp;
        this.mauSac = mauSac;
        this.size = size;
        this.setGiaBan(giaBan);
        this.setSoLuongTon(soLuongTon);
        this.setTrangThai(trangThai);
        this.setNgayTao(ngayTao);
        this.setNgaySua(ngaySua);

    }

    public CtspDetail() {

    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

