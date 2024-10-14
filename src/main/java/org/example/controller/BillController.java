package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.Hdct;
import org.example.entity.HoaDon;
import org.example.repository.impl.HdctRepositoryImpl;
import org.example.repository.impl.HoaDonRepositoryImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BillServlet", value = {"/bill/view","/bill/detail"})
public class BillController extends HttpServlet {
    private final HoaDonRepositoryImpl hoaDonRepository= new HoaDonRepositoryImpl();
    private final HdctRepositoryImpl hdctRepository= new HdctRepositoryImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/bill/view")) {
            List<HoaDon> hoaDons= hoaDonRepository.xemHD();
            req.setAttribute("hoaDons", hoaDons);
            req.getRequestDispatcher("/bill.jsp").forward(req, resp);
        }
        else if (uri.contains("/bill/detail")) {
            int id= Integer.parseInt(req.getParameter("id"));
            List<Hdct> hdcts=hdctRepository.getHDCTById(id);
            req.setAttribute("hdcts", hdcts);
            req.getRequestDispatcher("/billdetail.jsp").forward(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
