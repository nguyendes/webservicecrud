package org.example.repository.impl;

import org.example.entity.Hdct;
import org.example.repository.HdctRepository;
import org.example.util.DriverManagerConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HdctRepositoryImpl implements HdctRepository {

    @Override
    public List<Hdct> getAllHDCT() {
        List<Hdct> list = new ArrayList<>();
        String sql = "SELECT * FROM hdct";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Hdct hdct = new Hdct();
                hdct.setId(rs.getInt("id"));
                hdct.setIdHoaDon(rs.getInt("id_hoa_don"));
                hdct.setIdCtsp(rs.getInt("id_ctsp"));
                hdct.setSoLuongMua(rs.getInt("so_luong_mua"));
                hdct.setGiaBan(rs.getDouble("gia_ban"));
                hdct.setTongTien(rs.getDouble("tong_tien"));
                hdct.setTrangThai(rs.getString("trang_thai"));
                hdct.setNgayTao(rs.getDate("ngay_tao"));
                hdct.setNgaySua(rs.getDate("ngay_sua"));

                list.add(hdct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean themHDCT(Hdct hdct, int idHoaDon) {
        String sql = "INSERT INTO hdct (id_hoa_don, id_ctsp, so_luong_mua, gia_ban, tong_tien, trang_thai, ngay_tao) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idHoaDon);
            ps.setInt(2, hdct.getIdCtsp());
            ps.setInt(3, hdct.getSoLuongMua());
            ps.setDouble(4, hdct.getGiaBan());
            ps.setDouble(5, hdct.getTongTien());
            ps.setString(6, hdct.getTrangThai());
            ps.setDate(7, new java.sql.Date(hdct.getNgayTao().getTime()));

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean capnhatHD(Hdct hdct) {
        String sql = "UPDATE hdct SET id_hoa_don = ?, id_ctsp = ?, so_luong_mua = ?, gia_ban = ?, tong_tien = ?, trang_thai = ?, ngay_sua = ? WHERE id = ?";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, hdct.getIdHoaDon());
            ps.setInt(2, hdct.getIdCtsp());
            ps.setInt(3, hdct.getSoLuongMua());
            ps.setDouble(4, hdct.getGiaBan());
            ps.setDouble(5, hdct.getTongTien());
            ps.setString(6, hdct.getTrangThai());
            ps.setDate(7, new java.sql.Date(hdct.getNgaySua().getTime()));
            ps.setInt(8, hdct.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean xoaHDCT(int id) {
        String sql = "DELETE FROM hdct WHERE id = ?";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Hdct getHDCTById(int id) {
        Hdct hdct = null;
        String sql = "SELECT * FROM hdct WHERE id = ?";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    hdct = new Hdct();
                    hdct.setId(rs.getInt("id"));
                    hdct.setIdHoaDon(rs.getInt("id_hoa_don"));
                    hdct.setIdCtsp(rs.getInt("id_ctsp"));
                    hdct.setSoLuongMua(rs.getInt("so_luong_mua"));
                    hdct.setGiaBan(rs.getDouble("gia_ban"));
                    hdct.setTongTien(rs.getDouble("tong_tien"));
                    hdct.setTrangThai(rs.getString("trang_thai"));
                    hdct.setNgayTao(rs.getDate("ngay_tao"));
                    hdct.setNgaySua(rs.getDate("ngay_sua"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hdct;
    }
}
