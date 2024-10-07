package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.*;
import org.example.repository.impl.CtspRepositoryImpl;
import org.example.repository.impl.HdctRepositoryImpl;
import org.example.repository.impl.HoaDonRepositoryImpl;
import org.example.repository.impl.KhachHangRepositoryImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "HoaDonController", value = {"/hoadon/them", "/hoadon/hien-thi", "/hoadon/tim-khach", "/hoadon/thanh-toan"})
public class HoaDonController extends HttpServlet {
    private final HoaDonRepositoryImpl hoaDonRepository = new HoaDonRepositoryImpl();
    private final CtspRepositoryImpl ctspRepository = new CtspRepositoryImpl();
    private final KhachHangRepositoryImpl khachHangRepository = new KhachHangRepositoryImpl();
    private final HdctRepositoryImpl hoaDonChiTietService = new HdctRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        if (uri.contains("/hoadon/hien-thi")) {
            // Hiển thị danh sách sản phẩm và hóa đơn đang xử lý
            List<CtspDetail> ctspDetails = ctspRepository.getAllCtspDetails();
            req.setAttribute("ctspDetails", ctspDetails);
            List<HoaDon> pendingInvoices = hoaDonRepository.getAllHD(); // Lấy danh sách hóa đơn đang xử lý
            req.setAttribute("pendingInvoices", pendingInvoices);
            req.getRequestDispatcher("/hoadon.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("tim-khach".equals(action)) {
            // Tìm khách hàng theo số điện thoại
            String soDienThoai = req.getParameter("soDienThoai");
            KhachHang khachHang = khachHangRepository.findKhachHangBySdt(soDienThoai);
                req.setAttribute("khachHang", khachHang);
                List<CtspDetail> ctspDetails = ctspRepository.getAllCtspDetails();
                req.setAttribute("ctspDetails", ctspDetails);
                req.getRequestDispatcher("/hoadon.jsp").forward(req, resp);
        }

        if ("them".equals(action)) {
            // Thêm hóa đơn mới
            String soDienThoai = req.getParameter("soDienThoai");
            KhachHang khachHang = khachHangRepository.findKhachHangBySdt(soDienThoai);

                String diaChi = req.getParameter("diaChi");
                String trangThai = req.getParameter("trangThai");

                // Tạo đối tượng hóa đơn
                HoaDon hoaDon = new HoaDon();
                hoaDon.setIdKhachHang(khachHang.getId());
                hoaDon.setDiaChi(diaChi);
                hoaDon.setSoDienThoai(soDienThoai);
                hoaDon.setTrangThai(trangThai);
                hoaDon.setNgayTao(new Date());

                boolean isAdded = hoaDonRepository.themHD(hoaDon, soDienThoai);
                    resp.sendRedirect("/hoadon/hien-thi");
        }

        if ("thanh-toan".equals(action)) {
            int idHoaDon = Integer.parseInt(req.getParameter("id"));
            List<CtspUpdateInfo> updates = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                int idSp = Integer.parseInt(req.getParameter("idSp" + i));
                int soLuongMua = Integer.parseInt(req.getParameter("soLuongMua" + i));
                double giaBan= Double.parseDouble(req.getParameter("giaBan" + i));
                double tongTien= Double.parseDouble(req.getParameter("tongTien" + i));

                // Thêm vào danh sách cập nhật
                updates.add(new CtspUpdateInfo(idSp, soLuongMua));

                // Tạo chi tiết hóa đơn
                Hdct hoaDonChiTiet = new Hdct();
                hoaDonChiTiet.setIdHoaDon(idHoaDon);
                hoaDonChiTiet.setIdCtsp(idSp);
                hoaDonChiTiet.setSoLuongMua(soLuongMua);
                hoaDonChiTiet.setGiaBan(giaBan);
                hoaDonChiTiet.setTongTien(tongTien);
                hoaDonChiTiet.setNgayTao(new java.util.Date());

                hoaDonChiTietService.themHDCT(hoaDonChiTiet, idHoaDon);
            }
            ctspRepository.updateCtsp(updates);
            hoaDonRepository.thanhtoanHoaDon(idHoaDon);
            resp.sendRedirect("/hoadon/hien-thi");

        }

    }
}
