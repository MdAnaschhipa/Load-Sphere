package com.rpc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.*;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        res.setStatus(HttpServletResponse.SC_OK);
        res.getWriter().print("{\"success\":true,\"message\":\"Logged out successfully.\"}");
    }
}
