package org.example.repository;

import org.example.entity.HoaDon;
import org.example.entity.SanPham;

import java.util.List;

public interface HoaDonRepository {
    List<HoaDon> getAllHD();
    boolean themHD(HoaDon hd, String soDienThoai);
    boolean capnhatHD(HoaDon hd);
    boolean xoaHD(int id);
    HoaDon getHDById(int id);
    boolean thanhtoanHoaDon(int id);
}
