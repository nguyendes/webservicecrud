package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.repository.impl.UserRepositoryImpl;
import org.example.util.PasswordUtil;


import java.io.IOException;

@WebServlet(value = "/login")
public class LoginController extends HttpServlet {

    private UserRepositoryImpl userRepo = new UserRepositoryImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username + password);
        User user = userRepo.findByUsername(username);
        System.out.println("Hashed Password from DB: " + user.getPassword());
        password= PasswordUtil.hashPassword(password);
        System.out.println(password);
        if (PasswordUtil.checkPassword(password, user.getPassword())) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/index");


        } else {
            req.setAttribute("errorMessage", "Invalid username or password");
            req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
        }
    }


}

