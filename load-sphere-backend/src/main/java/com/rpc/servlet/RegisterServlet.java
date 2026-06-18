package com.rpc.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.rpc.dao.UserDAO;
import com.rpc.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.*;

public class RegisterServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();
    private final Gson    gson    = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        JsonObject body = gson.fromJson(req.getReader(), JsonObject.class);

        User user = new User();
        user.setName(body.get("name").getAsString());
        user.setEmail(body.get("email").getAsString());
        user.setPassword(body.get("password").getAsString());
        user.setPhone(body.get("phone").getAsString());
        user.setRole("owner"); // default role

        JsonObject response = new JsonObject();

        // Check if email already exists
        if (userDAO.findByEmail(user.getEmail()) != null) {
            response.addProperty("success", false);
            response.addProperty("message", "Email already registered.");
            res.setStatus(HttpServletResponse.SC_CONFLICT);
        } else if (userDAO.registerUser(user)) {
            response.addProperty("success", true);
            response.addProperty("message", "Registration successful.");
            res.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.addProperty("success", false);
            response.addProperty("message", "Registration failed. Please try again.");
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        res.getWriter().print(response.toString());
    }
}
