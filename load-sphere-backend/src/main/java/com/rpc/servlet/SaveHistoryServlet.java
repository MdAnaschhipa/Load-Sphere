package com.rpc.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.rpc.dao.ProfitDAO;
import com.rpc.model.ProfitHistory;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.*;

public class SaveHistoryServlet extends HttpServlet {

    private final ProfitDAO profitDAO = new ProfitDAO();
    private final Gson      gson      = new Gson();

    // POST /api/history — save a trip decision
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().print("{\"message\":\"Not logged in.\"}");
            return;
        }

        int userId = (int) session.getAttribute("userId");

        JsonObject body = gson.fromJson(req.getReader(), JsonObject.class);

        ProfitHistory history = new ProfitHistory();
        history.setUserId(userId);
        history.setLoadId(body.get("loadId").getAsInt());
        history.setTotalCost(body.get("totalCost").getAsDouble());
        history.setProfitAmt(body.get("profit").getAsDouble());
        history.setDecision(body.get("decision").getAsString());

        JsonObject response = new JsonObject();

        if (profitDAO.saveProfit(history)) {
            response.addProperty("success", true);
            response.addProperty("message", "Trip saved to history.");
            res.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.addProperty("success", false);
            response.addProperty("message", "Failed to save history.");
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        res.getWriter().print(response.toString());
    }

    // GET /api/history — retrieve trip history for logged-in user
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().print("{\"message\":\"Not logged in.\"}");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        res.setStatus(HttpServletResponse.SC_OK);
        res.getWriter().print(gson.toJson(profitDAO.getHistoryByUser(userId)));
    }
}
