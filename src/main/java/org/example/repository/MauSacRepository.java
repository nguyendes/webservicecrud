package org.example.repository;

import org.example.entity.MauSac;

import java.util.List;

public interface MauSacRepository {
    boolean add(MauSac mauSac);
    MauSac findByID(int id);
    List<MauSac> findAll();
    boolean update(MauSac mauSac);
    boolean delete(int id);
}
