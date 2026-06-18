package com.rpc.servlet;

import com.google.gson.Gson;
import com.rpc.dao.LoadDAO;
import com.rpc.model.Load;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.List;

public class LoadListServlet extends HttpServlet {

    private final LoadDAO loadDAO = new LoadDAO();
    private final Gson    gson    = new Gson();

    // GET /api/loads — return all loads
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        // Session check
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().print("{\"message\":\"Not logged in.\"}");
            return;
        }

        List<Load> loads = loadDAO.getAllLoads();
        res.setStatus(HttpServletResponse.SC_OK);
        res.getWriter().print(gson.toJson(loads));
    }
}
