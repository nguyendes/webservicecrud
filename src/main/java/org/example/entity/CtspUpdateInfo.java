package org.example.entity;

public class CtspUpdateInfo {
    private int id;
    private int soLuong;

    public CtspUpdateInfo(int id, int soLuong) {
        this.id = id;
        this.soLuong = soLuong;
    }

    public int getId() {
        return id;
    }

    public int getSoLuong() {
        return soLuong;
    }
}

