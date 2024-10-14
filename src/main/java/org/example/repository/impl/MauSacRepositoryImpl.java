package org.example.repository.impl;

import org.example.entity.MauSac;
import org.example.repository.MauSacRepository;
import org.example.util.DriverManagerConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MauSacRepositoryImpl implements MauSacRepository {

    @Override
    public List<MauSac> findAll() {
        List<MauSac> list = new ArrayList<>();
        String sql = "SELECT * FROM mau_sac";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                MauSac mauSac = new MauSac();
                mauSac.setId(rs.getInt("id"));
                mauSac.setMaMau(rs.getString("ma_mau"));
                mauSac.setTenMau(rs.getString("ten_mau"));
                mauSac.setTrangThai(rs.getString("trang_thai"));
                mauSac.setNgayTao(rs.getDate("ngay_tao"));
                mauSac.setNgaySua(rs.getDate("ngay_sua"));
                list.add(mauSac);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public MauSac findByID(int id) {
        String sql = "SELECT * FROM mau_sac WHERE id = ?";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    MauSac mauSac = new MauSac();
                    mauSac.setId(rs.getInt("id"));
                    mauSac.setMaMau(rs.getString("ma_mau"));
                    mauSac.setTenMau(rs.getString("ten_mau"));
                    mauSac.setTrangThai(rs.getString("trang_thai"));
                    mauSac.setNgayTao(rs.getDate("ngay_tao"));
                    mauSac.setNgaySua(rs.getDate("ngay_sua"));
                    return mauSac;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(MauSac mauSac) {
        String sql = "INSERT INTO mau_sac (ma_mau, ten_mau, trang_thai, ngay_tao, ngay_sua) VALUES (?, ?, ?, NOW(), NOW())";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, mauSac.getMaMau());
            ps.setString(2, mauSac.getTenMau());
            ps.setString(3, mauSac.getTrangThai());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(MauSac mauSac) {
        String sql = "UPDATE mau_sac SET ma_mau = ?, ten_mau = ?, trang_thai = ?, ngay_sua = NOW() WHERE id = ?";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, mauSac.getMaMau());
            ps.setString(2, mauSac.getTenMau());
            ps.setString(3, mauSac.getTrangThai());
            ps.setInt(4, mauSac.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM mau_sac WHERE id = ?";
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

