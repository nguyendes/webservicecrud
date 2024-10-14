package org.example.repository;

import org.example.entity.DanhMuc;

import java.util.List;

public interface DanhMucRepository {
    boolean add(DanhMuc danhMuc);
    DanhMuc findById(int id);
    List<DanhMuc> findAll();
    boolean update(DanhMuc danhMuc);
    boolean delete(int id);
}
