package org.example.repository;

import org.example.entity.Size;

import java.util.List;

public interface SizeRepository {
    boolean add(Size size);
    Size findById(int id);
    List<Size> findAll();
    boolean update(Size size);
    boolean delete(int id);
}

