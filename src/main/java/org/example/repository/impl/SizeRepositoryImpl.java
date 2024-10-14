package org.example.repository.impl;

import org.example.entity.Size;
import org.example.repository.SizeRepository;
import org.example.util.DriverManagerConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SizeRepositoryImpl implements SizeRepository {

    @Override
    public List<Size> findAll() {
        List<Size> list = new ArrayList<>();
        String sql = "SELECT * FROM size";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Size size = new Size();
                size.setId(rs.getInt("id"));
                size.setMaSize(rs.getString("ma_size"));
                size.setTenSize(rs.getString("ten_size"));
                size.setTrangThai(rs.getString("trang_thai"));
                size.setNgayTao(rs.getDate("ngay_tao"));
                size.setNgaySua(rs.getDate("ngay_sua"));
                list.add(size);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Size findById(int id) {
        String sql = "SELECT * FROM size WHERE id = ?";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Size size = new Size();
                    size.setId(rs.getInt("id"));
                    size.setMaSize(rs.getString("ma_size"));
                    size.setTenSize(rs.getString("ten_size"));
                    size.setTrangThai(rs.getString("trang_thai"));
                    size.setNgayTao(rs.getDate("ngay_tao"));
                    size.setNgaySua(rs.getDate("ngay_sua"));
                    return size;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(Size size) {
        String sql = "INSERT INTO size (ma_size, ten_size, trang_thai, ngay_tao, ngay_sua) VALUES (?, ?, ?, NOW(), NOW())";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, size.getMaSize());
            ps.setString(2, size.getTenSize());
            ps.setString(3, size.getTrangThai());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Size size) {
        String sql = "UPDATE size SET ma_size = ?, ten_size = ?, trang_thai = ?, ngay_sua = NOW() WHERE id = ?";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, size.getMaSize());
            ps.setString(2, size.getTenSize());
            ps.setString(3, size.getTrangThai());
            ps.setInt(4, size.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM size WHERE id = ?";
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

