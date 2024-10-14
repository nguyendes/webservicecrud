package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.DanhMuc;
import org.example.entity.MauSac;
import org.example.entity.Size;
import org.example.repository.impl.DanhMucRepositoryImpl;
import org.example.repository.impl.MauSacRepositoryImpl;
import org.example.repository.impl.SizeRepositoryImpl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "CauHinhServlet", value = {"/show", "/mausac/them", "/mausac/sua", "/mausac/xoa",
        "/size/them", "/size/sua", "/size/xoa",
        "/danhmuc/them", "/danhmuc/sua", "/danhmuc/xoa"})
public class CauHinhSanPhamController extends HttpServlet {
    private final MauSacRepositoryImpl mauSacRepo = new MauSacRepositoryImpl();
    private final SizeRepositoryImpl sizeRepo = new SizeRepositoryImpl();
    private final DanhMucRepositoryImpl danhMucRepo = new DanhMucRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        if (uri.contains("/show")) {
            // Display all items
            List<MauSac> mauSacList = mauSacRepo.findAll();
            List<Size> sizeList = sizeRepo.findAll();
            List<DanhMuc> danhMucList = danhMucRepo.findAll();

            req.setAttribute("mauSacList", mauSacList);
            req.setAttribute("sizeList", sizeList);
            req.setAttribute("danhMucList", danhMucList);

            req.getRequestDispatcher("/cauhinhsanpham.jsp").forward(req, resp);
        }

        // Deletion
        if (uri.contains("/mausac/xoa")) {
            Integer mauSacId = Integer.parseInt(req.getParameter("mauSacId"));
            mauSacRepo.delete(mauSacId);
        } else if (uri.contains("/size/xoa")) {
            Integer sizeId = Integer.parseInt(req.getParameter("sizeId"));
            sizeRepo.delete(sizeId);
        } else if (uri.contains("/danhmuc/xoa")) {
            Integer danhMucId = Integer.parseInt(req.getParameter("danhMucId"));
            danhMucRepo.delete(danhMucId);
        }

        resp.sendRedirect(req.getContextPath() + "/show");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        Date currentDate = new Date(); // Get current date


        // MauSac operations (Add/Update)
        if (uri.contains("/mausac/sua")) {
            Integer id = req.getParameter("id").isEmpty() ? null : Integer.parseInt(req.getParameter("id"));
            String maMau = req.getParameter("maMau");
            String tenMau = req.getParameter("tenMau");
            String trangThai = req.getParameter("trangThai");
////
////
////            MauSac mauSac = new MauSac(, maMau, tenMau, trangThai, currentDate,currentDate);
//            if (id == null) {
//                mauSacRepo.add(mauSac); // Insert
//            } else {
//                mauSacRepo.update(mauSac); // Update
//            }
        }

        // Size operations (Add/Update)
        if (uri.contains("/size/sua")) {
            Integer id = req.getParameter("id").isEmpty() ? null : Integer.parseInt(req.getParameter("id"));
            String maSize = req.getParameter("maSize");
            String tenSize = req.getParameter("tenSize");
            String trangThai = req.getParameter("trangThai");

//            Size size = new Size(id, maSize, tenSize, trangThai);
//            if (id == null) {
//                sizeRepo.add(size); // Insert
//            } else {
//                sizeRepo.update(size); // Update
//            }
        }

        // DanhMuc operations (Add/Update)
        if (uri.contains("/danhmuc/sua")) {
            Integer id = req.getParameter("id").isEmpty() ? null : Integer.parseInt(req.getParameter("id"));
            String maDanhMuc = req.getParameter("maDanhMuc");
            String tenDanhMuc = req.getParameter("tenDanhMuc");
            String trangThai = req.getParameter("trangThai");

//            DanhMuc danhMuc = new DanhMuc(id, maDanhMuc, tenDanhMuc, trangThai);
//            if (id == null) {
//                danhMucRepo.add(danhMuc); // Insert
//            } else {
//                danhMucRepo.update(danhMuc); // Update
//            }
//        }

            resp.sendRedirect(req.getContextPath() + "/show");
        }
    }
}