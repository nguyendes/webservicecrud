package org.example.repository;

import org.example.entity.KhachHang;

import java.util.List;

public interface KhachHangRepository {
    List<KhachHang> findAllKHs();
    KhachHang findKhachHangBySdt(String sdt);
    boolean addKH(KhachHang khachHang);
    boolean updateKH(KhachHang khachHang);
    boolean deleteKH(int id);

}
