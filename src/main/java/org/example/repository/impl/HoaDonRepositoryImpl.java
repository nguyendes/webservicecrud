package org.example.repository.impl;

import org.example.entity.HoaDon;
import org.example.entity.KhachHang;
import org.example.entity.SanPham;
import org.example.repository.HoaDonRepository;
import org.example.repository.KhachHangRepository;
import org.example.repository.impl.KhachHangRepositoryImpl;
import org.example.util.DriverManagerConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HoaDonRepositoryImpl implements HoaDonRepository {

    private KhachHangRepository khachHangRepository = new KhachHangRepositoryImpl();


    @Override
    public List<HoaDon> getAllHD() {
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM db_ban_hang.hoa_don where trang_thai= 'Đang xử lý'";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt("id"));
                hd.setIdKhachHang(rs.getInt("id_khach_hang"));
                hd.setTrangThai(rs.getString("trang_thai"));
                hd.setNgayTao(rs.getDate("ngay_tao"));
                hd.setNgaySua(rs.getDate("ngay_sua"));
                hd.setDiaChi(rs.getString("dia_chi"));
                hd.setSoDienThoai(rs.getString("so_dien_thoai"));
                list.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean themHD(HoaDon hd, String soDienThoai) {
        // Tìm khách hàng theo số điện thoại
        KhachHang khachHang = khachHangRepository.findKhachHangBySdt(soDienThoai);
        if (khachHang != null) {
            int idKhachHang = khachHang.getId();
            hd.setIdKhachHang(idKhachHang); // Gán id khách hàng vào đối tượng hd

            String sql = "INSERT INTO hoa_don (id_khach_hang, ngay_tao, trang_thai, dia_chi, so_dien_thoai) VALUES (?, ?, ?, ?, ?)";
            try (Connection connection = DriverManagerConnection.getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, hd.getIdKhachHang());
                ps.setDate(2, new java.sql.Date(hd.getNgayTao().getTime()));
                ps.setString(3, hd.getTrangThai());
                ps.setString(4, hd.getDiaChi());
                ps.setString(5, hd.getSoDienThoai());

                return ps.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false; // Trả về false nếu không tìm thấy khách hàng
    }

    @Override
    public boolean capnhatHD(HoaDon hd) {
        String sql = "UPDATE hoa_don SET id_khach_hang = ?, ngay_tao = ?, trang_thai = ?, dia_chi = ?, so_dien_thoai = ? WHERE id = ?";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, hd.getIdKhachHang()); // Gán id khách hàng
            ps.setDate(2, new java.sql.Date(hd.getNgayTao().getTime()));
            ps.setString(3, hd.getTrangThai());
            ps.setString(4, hd.getDiaChi());
            ps.setString(5, hd.getSoDienThoai());
            ps.setInt(6, hd.getId()); // ID hóa đơn
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean xoaHD(int id) {
        String sql = "DELETE FROM hoa_don WHERE id = ?";
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
    public HoaDon getHDById(int id) {
        HoaDon hd = null;
        String sql = "SELECT * FROM hoa_don WHERE id = ?";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    hd = new HoaDon();
                    hd.setId(rs.getInt("id"));
                    hd.setIdKhachHang(rs.getInt("id_khach_hang")); // Sử dụng id_khach_hang
                    hd.setTrangThai(rs.getString("trang_thai"));
                    hd.setNgayTao(rs.getDate("ngay_tao"));
                    hd.setNgaySua(rs.getDate("ngay_sua")); // Nếu có trường này trong DB
                    hd.setDiaChi(rs.getString("dia_chi"));
                    hd.setSoDienThoai(rs.getString("so_dien_thoai"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hd;
    }
    @Override
    public boolean thanhtoanHoaDon(int idHoaDon) {
        String sql = "UPDATE hoa_don SET trang_thai = 'Đã thanh toán' WHERE id = ?";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idHoaDon);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
