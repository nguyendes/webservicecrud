package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.KhachHang;
import org.example.repository.impl.KhachHangRepositoryImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "KhachHangServlet", value = {"/khachhang/view", "/khachhang/them", "/khachhang/sua", "/khachhang/xoa"})
public class KhachHangController extends HttpServlet {
    private final KhachHangRepositoryImpl khachHangRepository = new KhachHangRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        if (uri.equals("/khachhang/view")) {
            List<KhachHang> khachHangList = khachHangRepository.findAllKHs();
            req.setAttribute("khachHangList", khachHangList);
            req.getRequestDispatcher("/khachhang.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String hoTen = req.getParameter("hoTen");
        String diaChi = req.getParameter("diaChi");
        String sdt = req.getParameter("sdt");
        String trangThai = req.getParameter("trangThai");

        if (action.equals("them")) {
            KhachHang newKhachHang = new KhachHang();
            newKhachHang.setHoTen(hoTen);
            newKhachHang.setDiaChi(diaChi);
            newKhachHang.setSdt(sdt);
            newKhachHang.setTrangThai(trangThai);

            khachHangRepository.addKH(newKhachHang);
        } else if (action.equals("capnhat") ) {

            KhachHang khachHang = new KhachHang();
                khachHang.setHoTen(hoTen);
                khachHang.setDiaChi(diaChi);
                khachHang.setSdt(sdt);
                khachHang.setTrangThai(trangThai);

                khachHangRepository.updateKH(khachHang);
            }

        resp.sendRedirect("/khachhang/view");}
    }

