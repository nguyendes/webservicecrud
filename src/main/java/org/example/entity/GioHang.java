package org.example.entity;

public class GioHang extends SanPham{
    private String size;
    private double giaBan;
    private int soLuongTon;

    public GioHang( String size, double giaBan, int soLuongTon) {
        this.size = size;
        this.giaBan = giaBan;
        this.soLuongTon = soLuongTon;
    }

    public GioHang() {
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }
}