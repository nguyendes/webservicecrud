package org.example.repository.impl;

import org.example.entity.DanhMuc;
import org.example.repository.DanhMucRepository;
import org.example.util.DriverManagerConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DanhMucRepositoryImpl implements DanhMucRepository {

    @Override
    public List<DanhMuc> findAll() {
        List<DanhMuc> list = new ArrayList<>();
        String sql = "SELECT * FROM danh_muc";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                DanhMuc danhMuc = new DanhMuc();
                danhMuc.setId(rs.getInt("id"));
                danhMuc.setMaDanhMuc(rs.getString("ma_danh_muc"));
                danhMuc.setTenDanhMuc(rs.getString("ten_danh_muc"));
                danhMuc.setTrangThai(rs.getString("trang_thai"));
                danhMuc.setNgayTao(rs.getDate("ngay_tao"));
                danhMuc.setNgaySua(rs.getDate("ngay_sua"));
                list.add(danhMuc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public DanhMuc findById(int id) {
        String sql = "SELECT * FROM danh_muc WHERE id = ?";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    DanhMuc danhMuc = new DanhMuc();
                    danhMuc.setId(rs.getInt("id"));
                    danhMuc.setMaDanhMuc(rs.getString("ma_danh_muc"));
                    danhMuc.setTenDanhMuc(rs.getString("ten_danh_muc"));
                    danhMuc.setTrangThai(rs.getString("trang_thai"));
                    danhMuc.setNgayTao(rs.getDate("ngay_tao"));
                    danhMuc.setNgaySua(rs.getDate("ngay_sua"));
                    return danhMuc;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(DanhMuc danhMuc) {
        String sql = "INSERT INTO danh_muc (ma_danh_muc, ten_danh_muc, trang_thai, ngay_tao, ngay_sua) VALUES (?, ?, ?, NOW(), NOW())";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, danhMuc.getMaDanhMuc());
            ps.setString(2, danhMuc.getTenDanhMuc());
            ps.setString(3, danhMuc.getTrangThai());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(DanhMuc danhMuc) {
        String sql = "UPDATE danh_muc SET ma_danh_muc = ?, ten_danh_muc = ?, trang_thai = ?, ngay_sua = NOW() WHERE id = ?";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, danhMuc.getMaDanhMuc());
            ps.setString(2, danhMuc.getTenDanhMuc());
            ps.setString(3, danhMuc.getTrangThai());
            ps.setInt(4, danhMuc.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM danh_muc WHERE id = ?";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
