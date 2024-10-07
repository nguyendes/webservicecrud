package org.example.repository;

import org.example.entity.SanPham;

import java.util.List;

public interface SanPhamRepository {
    List<SanPham> getAllSanPham();
    boolean themSanPham(SanPham sp);
    boolean capnhatSanPham(SanPham sp);
    boolean xoaSanPham(int spID); //
    SanPham getProductById(int spID); //
}
