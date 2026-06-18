package com.rpc.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.rpc.dao.LoadDAO;
import com.rpc.model.Load;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.*;

public class CalculateServlet extends HttpServlet {

    private final LoadDAO loadDAO = new LoadDAO();
    private final Gson    gson    = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
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

        JsonObject body = gson.fromJson(req.getReader(), JsonObject.class);

        int    loadId          = body.get("loadId").getAsInt();
        double fuelRate        = body.get("fuelRate").getAsDouble();        // ₹ per litre
        double mileageKmpl     = body.get("mileageKmpl").getAsDouble();     // km per litre
        double tollCharges     = body.get("tollCharges").getAsDouble();      // ₹
        double driverAllowance = body.get("driverAllowance").getAsDouble();  // ₹

        // Fetch load details from DB
        Load load = loadDAO.getLoadById(loadId);
        JsonObject response = new JsonObject();

        if (load == null) {
            response.addProperty("success", false);
            response.addProperty("message", "Load not found.");
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            res.getWriter().print(response.toString());
            return;
        }

        // === Core Calculation ===
        // Fuel Cost = (Distance ÷ Mileage) × Fuel Rate per litre
        double fuelCost  = (load.getDistanceKm() / mileageKmpl) * fuelRate;
        double totalCost = fuelCost + tollCharges + driverAllowance;
        double profit    = load.getQuotedRate() - totalCost;
        String decision  = profit >= 0 ? "Accept" : "Reject";

        response.addProperty("success",         true);
        response.addProperty("loadId",          loadId);
        response.addProperty("origin",          load.getOrigin());
        response.addProperty("destination",     load.getDestination());
        response.addProperty("distanceKm",      load.getDistanceKm());
        response.addProperty("quotedRate",      load.getQuotedRate());
        response.addProperty("fuelCost",        Math.round(fuelCost  * 100.0) / 100.0);
        response.addProperty("tollCharges",     tollCharges);
        response.addProperty("driverAllowance", driverAllowance);
        response.addProperty("totalCost",       Math.round(totalCost * 100.0) / 100.0);
        response.addProperty("profit",          Math.round(profit    * 100.0) / 100.0);
        response.addProperty("decision",        decision);

        res.setStatus(HttpServletResponse.SC_OK);
        res.getWriter().print(response.toString());
    }
}
