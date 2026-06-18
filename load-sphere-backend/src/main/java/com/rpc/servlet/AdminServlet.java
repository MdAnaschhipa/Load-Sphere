package com.rpc.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.rpc.dao.LoadDAO;
import com.rpc.model.Load;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.*;

public class AdminServlet extends HttpServlet {

    private final LoadDAO loadDAO = new LoadDAO();
    private final Gson    gson    = new Gson();

    // POST /api/admin/load — add new load
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        if (!isAdmin(req)) {
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
            res.getWriter().print("{\"message\":\"Admin access only.\"}");
            return;
        }

        JsonObject body = gson.fromJson(req.getReader(), JsonObject.class);

        Load load = new Load();
        load.setOrigin(body.get("origin").getAsString());
        load.setDestination(body.get("destination").getAsString());
        load.setCargoType(body.get("cargoType").getAsString());
        load.setWeightTons(body.get("weightTons").getAsDouble());
        load.setDistanceKm(body.get("distanceKm").getAsDouble());
        load.setQuotedRate(body.get("quotedRate").getAsDouble());

        JsonObject response = new JsonObject();

        if (loadDAO.addLoad(load)) {
            response.addProperty("success", true);
            response.addProperty("message", "Load added successfully.");
            res.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.addProperty("success", false);
            response.addProperty("message", "Failed to add load.");
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        res.getWriter().print(response.toString());
    }

    // DELETE /api/admin/load?loadId=5 — remove a load
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        if (!isAdmin(req)) {
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
            res.getWriter().print("{\"message\":\"Admin access only.\"}");
            return;
        }

        int loadId = Integer.parseInt(req.getParameter("loadId"));
        JsonObject response = new JsonObject();

        if (loadDAO.deleteLoad(loadId)) {
            response.addProperty("success", true);
            response.addProperty("message", "Load deleted.");
            res.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.addProperty("success", false);
            response.addProperty("message", "Delete failed.");
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        res.getWriter().print(response.toString());
    }

    private boolean isAdmin(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return session != null &&
               "admin".equals(session.getAttribute("role"));
    }
}
