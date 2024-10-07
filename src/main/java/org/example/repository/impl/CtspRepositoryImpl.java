package org.example.repository.impl;

import org.example.entity.Ctsp;
import org.example.entity.CtspDetail;
import org.example.entity.CtspUpdateInfo;
import org.example.repository.CtspRepository;
import org.example.util.DriverManagerConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CtspRepositoryImpl implements CtspRepository {
    @Override
    public List<CtspDetail> getAllCtspDetails() {
        List<CtspDetail> ctspList = new ArrayList<>();
        String sql = "SELECT \n" +
                "    sp.id AS id_san_pham, \n" +
                "    sp.ma_san_pham AS ma_san_pham, \n" +
                "    sp.ten_san_pham AS ten_san_pham, \n" +
                "    ms.ten_mau AS mau_sac, \n" +
                "    s.ten_size AS size, \n" +
                "    ctsp.gia_ban, \n" +
                "    ctsp.so_luong_ton, \n" +
                "    ctsp.trang_thai, \n" +
                "    ctsp.ngay_tao, \n" +
                "    ctsp.ngay_sua\n" +
                "FROM \n" +
                "    ctsp\n" +
                "JOIN \n" +
                "    san_pham sp ON ctsp.id_sp = sp.id\n" +
                "JOIN \n" +
                "    mau_sac ms ON ctsp.id_mau_sac = ms.id\n" +
                "JOIN \n" +
                "    size s ON ctsp.id_size = s.id;\n";

        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CtspDetail ctspd = new CtspDetail();

                // Gán giá trị từ ResultSet vào các trường của CtspDetail
                ctspd.setId(rs.getInt("id_san_pham"));
                ctspd.setMaSanPham(rs.getString("ma_san_pham"));
                ctspd.setTenSp(rs.getString("ten_san_pham"));
                ctspd.setMauSac(rs.getString("mau_sac"));
                ctspd.setSize(rs.getString("size"));
                ctspd.setGiaBan(rs.getDouble("gia_ban"));
                ctspd.setSoLuongTon(rs.getInt("so_luong_ton"));
                ctspd.setTrangThai(rs.getString("trang_thai"));
                ctspd.setNgayTao(rs.getDate("ngay_tao"));
                ctspd.setNgaySua(rs.getDate("ngay_sua"));

                // Thêm vào danh sách ctspList
                ctspList.add(ctspd);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Xử lý ngoại lệ
            throw new RuntimeException(e);
        }

        return ctspList;
    }


    @Override
    public CtspDetail getCtspById(int idSp) {
        CtspDetail ctspDetail = null;
        String sql = "SELECT \n" +
                "    ctsp.id AS id, \n" +
                "    sp.ten_san_pham AS tensp, \n" +
                "    ms.ten_mau AS mausac, \n" +
                "    s.ten_size AS size, \n" +
                "    ctsp.gia_ban, \n" +
                "    ctsp.so_luong_ton, \n" +
                "    ctsp.trang_thai, \n" +
                "    ctsp.ngay_tao, \n" +
                "    ctsp.ngay_sua \n" +
                "FROM \n" +
                "    ctsp \n" +
                "JOIN \n" +
                "    san_pham sp ON ctsp.id_sp = sp.id \n" +
                "JOIN \n" +
                "    mau_sac ms ON ctsp.id_mau_sac = ms.id \n" +
                "JOIN \n" +
                "    size s ON ctsp.id_size = s.id \n" +
                "WHERE \n" +
                "    ctsp.id_sp = ?;";

        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idSp);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ctspDetail = new CtspDetail();
                    ctspDetail.setId(rs.getInt("id"));
                    ctspDetail.setTenSp(rs.getString("tensp"));
                    ctspDetail.setMauSac(rs.getString("mausac"));
                    ctspDetail.setSize(rs.getString("size"));
                    ctspDetail.setGiaBan(rs.getDouble("gia_ban"));
                    ctspDetail.setSoLuongTon(rs.getInt("so_luong_ton"));
                    ctspDetail.setTrangThai(rs.getString("trang_thai"));
                    ctspDetail.setNgayTao(rs.getDate("ngay_tao"));
                    ctspDetail.setNgaySua(rs.getDate("ngay_sua"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ctspDetail;
    }

    @Override
    public boolean addCtsp(Ctsp ctsp) {
        String sql = "INSERT INTO ctsp (id_sp, id_mau_sac, id_size, gia_ban, so_luong_ton, trang_thai, ngay_tao, ngay_sua) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManagerConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, ctsp.getIdSp());
            ps.setInt(2, ctsp.getIdMauSac());
            ps.setInt(3, ctsp.getIdSize());
            ps.setDouble(4, ctsp.getGiaBan());
            ps.setInt(5, ctsp.getSoLuongTon());
            ps.setString(6, ctsp.getTrangThai());
            ps.setDate(7, new java.sql.Date(ctsp.getNgayTao().getTime()));
            ps.setDate(8, new java.sql.Date(ctsp.getNgaySua().getTime()));

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCtsp(List<CtspUpdateInfo> updates) {
        boolean isSuccess = true;

        // Kết nối đến cơ sở dữ liệu
        try (Connection connection = DriverManagerConnection.getConnection()) {
            connection.setAutoCommit(false); // Bắt đầu giao dịch

            for (CtspUpdateInfo update : updates) {
                String sql = "UPDATE ctsp SET so_luong_ton = so_luong_ton - ? WHERE id_sp = ?";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setInt(1, update.getSoLuong());
                    ps.setInt(2, update.getId());
                    ps.executeUpdate();
                }
            }

            connection.commit(); // Cam kết giao dịch
        } catch (SQLException e) {
            e.printStackTrace();
            isSuccess = false;
        }

        return isSuccess;
    }



    @Override
    public boolean deleteCtsp(int id) {
        String sql = "DELETE FROM ctsp WHERE id = ?";
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
