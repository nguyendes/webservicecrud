package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.CtspDetail;
import org.example.entity.SanPham;
import org.example.repository.impl.CtspRepositoryImpl;
import org.example.repository.impl.SanPhamRepositoryImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SanPhamServlet", value = {"/sanpham/hien-thi", "/sanpham/them", "/sanpham/xoa", "/sanpham/cap-nhat", "/sanpham/chi-tiet"})
public class SanPhamController extends HttpServlet {
    private final SanPhamRepositoryImpl sanPhamService = new SanPhamRepositoryImpl();
    private final CtspRepositoryImpl ctspService = new CtspRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        if (uri.contains("/sanpham/hien-thi")) {
            List<SanPham> sanPhams = sanPhamService.getAllSanPham();
            req.setAttribute("listProduct", sanPhams);
            req.getRequestDispatcher("/product.jsp").forward(req, resp);
        } else if (uri.contains("/sanpham/xoa")) {
            String id = req.getParameter("id");
            boolean isDeleted = sanPhamService.xoaSanPham(Integer.parseInt(id));
            if (isDeleted) {
                resp.sendRedirect("/sanpham/hien-thi");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Sản phẩm không tồn tại");
            }
        } else if (uri.contains("/sanpham/chi-tiet")) {
            String id = req.getParameter("id");
            CtspDetail spd = ctspService.getCtspById(Integer.parseInt(id));
            if (spd != null) {
                req.setAttribute("CtspDetail", spd);
                req.getRequestDispatcher("/detail.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Sản phẩm không tồn tại");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("them".equals(action)) {
            // Thêm sản phẩm mới
            String maSanPham = req.getParameter("maSanPham");
            String tenSanPham = req.getParameter("tenSanPham");
            String trangThai = req.getParameter("trangThai");
            int idDanhMuc = Integer.parseInt(req.getParameter("idDanhMuc"));

            SanPham sp = new SanPham();
            sp.setMaSanPham(maSanPham);
            sp.setTenSanPham(tenSanPham);
            sp.setTrangThai(trangThai);
            sp.setIdDanhMuc(idDanhMuc);

            boolean isAdded = sanPhamService.themSanPham(sp);
            if (isAdded) {
                resp.sendRedirect("/sanpham/hien-thi");
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thêm sản phẩm thất bại");
            }
        } else if ("capnhat".equals(action)) {
            // Cập nhật sản phẩm
            String id = req.getParameter("id");
            String maSanPham = req.getParameter("maSanPham");
            String tenSanPham = req.getParameter("tenSanPham");
            String trangThai = req.getParameter("trangThai");
            int idDanhMuc = Integer.parseInt(req.getParameter("idDanhMuc"));

            SanPham sp = new SanPham();
            sp.setId(Integer.parseInt(id));
            sp.setMaSanPham(maSanPham);
            sp.setTenSanPham(tenSanPham);
            sp.setTrangThai(trangThai);
            sp.setIdDanhMuc(idDanhMuc);

            boolean isUpdated = sanPhamService.capnhatSanPham(sp);
            if (isUpdated) {
                resp.sendRedirect("/sanpham/hien-thi");
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Cập nhật sản phẩm thất bại");
            }
        }
    }}
