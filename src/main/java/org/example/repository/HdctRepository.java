package org.example.repository;

import org.example.entity.Hdct;
import org.example.entity.HoaDon;

import java.util.List;

public interface HdctRepository {
    List<Hdct> getAllHDCT();
    boolean themHDCT(Hdct hdct,int idHoaDon);
    boolean capnhatHD(Hdct hdct);
    boolean xoaHDCT(int id);
    Hdct getHDCTById(int id);
}
