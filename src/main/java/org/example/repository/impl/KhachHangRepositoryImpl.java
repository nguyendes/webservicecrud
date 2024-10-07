package org.example.repository.impl;

import org.example.entity.KhachHang;
import org.example.repository.KhachHangRepository;
import org.example.util.DriverManagerConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHangRepositoryImpl implements KhachHangRepository {

    @Override
    public List<KhachHang> findAllKHs() {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM khach_hang";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setId(rs.getInt("id"));
                kh.setHoTen(rs.getString("ho_ten"));
                kh.setDiaChi(rs.getString("dia_chi"));
                kh.setSdt(rs.getString("sdt"));
                kh.setTrangThai(rs.getString("trang_thai"));
                kh.setNgayTao(rs.getDate("ngay_tao"));
                kh.setNgaySua(rs.getDate("ngay_sua"));
                list.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public KhachHang findKhachHangBySdt(String sdt) {
        String sql = "SELECT * FROM khach_hang WHERE sdt = ?";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, sdt);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    KhachHang kh = new KhachHang();
                    kh.setId(rs.getInt("id"));
                    kh.setHoTen(rs.getString("ho_ten"));
                    kh.setDiaChi(rs.getString("dia_chi"));
                    kh.setSdt(rs.getString("sdt"));
                    kh.setTrangThai(rs.getString("trang_thai"));
                    kh.setNgayTao(rs.getDate("ngay_tao"));
                    kh.setNgaySua(rs.getDate("ngay_sua"));
                    return kh;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addKH(KhachHang khachHang) {
        String sql = "INSERT INTO khach_hang (ho_ten, dia_chi, sdt, trang_thai, ngay_tao, ngay_sua) VALUES (?, ?, ?, ?, NOW(), NOW())";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, khachHang.getHoTen());
            ps.setString(2, khachHang.getDiaChi());
            ps.setString(3, khachHang.getSdt());
            ps.setString(4, khachHang.getTrangThai());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateKH(KhachHang khachHang) {
        String sql = "UPDATE khach_hang SET ho_ten = ?, dia_chi = ?, sdt = ?, trang_thai = ?, ngay_sua = NOW() WHERE id = ?";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, khachHang.getHoTen());
            ps.setString(2, khachHang.getDiaChi());
            ps.setString(3, khachHang.getSdt());
            ps.setString(4, khachHang.getTrangThai());
            ps.setInt(5, khachHang.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteKH(int id) {
        String sql = "DELETE FROM khach_hang WHERE id = ?";
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
