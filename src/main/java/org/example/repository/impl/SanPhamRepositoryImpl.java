package org.example.repository.impl;

import org.example.entity.SanPham;
import org.example.repository.SanPhamRepository;
import org.example.util.DriverManagerConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPhamRepositoryImpl implements SanPhamRepository {

    @Override
    public List<SanPham> getAllSanPham() {
        List<SanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM san_pham";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt("id"));
                sp.setMaSanPham(rs.getString("ma_san_pham"));
                sp.setTenSanPham(rs.getString("ten_san_pham"));
                sp.setTrangThai(rs.getString("trang_thai"));
                sp.setNgayTao(rs.getDate("ngay_tao"));
                sp.setNgaySua(rs.getDate("ngay_sua"));
                sp.setIdDanhMuc(rs.getInt("id_danh_muc"));
                list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean themSanPham(SanPham sp) {
        String sql = "INSERT INTO san_pham (ma_san_pham, ten_san_pham, trang_thai, ngay_tao, ngay_sua, id_danh_muc) VALUES (?, ?, ?, NOW(), NOW(), ?)";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, sp.getMaSanPham());
            ps.setString(2, sp.getTenSanPham());
            ps.setString(3, sp.getTrangThai());
            ps.setInt(4, sp.getIdDanhMuc());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean capnhatSanPham(SanPham sp) {
        String sql = "UPDATE san_pham SET ma_san_pham = ?, ten_san_pham = ?, trang_thai = ?, ngay_sua = NOW(), id_danh_muc = ? WHERE id = ?";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, sp.getMaSanPham());
            ps.setString(2, sp.getTenSanPham());
            ps.setString(3, sp.getTrangThai());
            ps.setInt(4, sp.getIdDanhMuc());
            ps.setInt(5, sp.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean xoaSanPham(int spID) {
        String sql = "DELETE FROM san_pham WHERE id = ?";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, spID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public SanPham getProductById(int spID) {
        String sql = "SELECT * FROM san_pham WHERE id = ?";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, spID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt("id"));
                sp.setMaSanPham(rs.getString("ma_san_pham"));
                sp.setTenSanPham(rs.getString("ten_san_pham"));
                sp.setTrangThai(rs.getString("trang_thai"));
                sp.setNgayTao(rs.getDate("ngay_tao"));
                sp.setNgaySua(rs.getDate("ngay_sua"));
                sp.setIdDanhMuc(rs.getInt("id_danh_muc"));
                return sp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
