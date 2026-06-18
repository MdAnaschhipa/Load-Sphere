package com.rpc.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.rpc.dao.UserDAO;
import com.rpc.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.*;

public class LoginServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();
    private final Gson    gson    = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        JsonObject body = gson.fromJson(req.getReader(), JsonObject.class);
        String email    = body.get("email").getAsString();
        String password = body.get("password").getAsString();

        User user = userDAO.validateLogin(email, password);
        JsonObject response = new JsonObject();

        if (user != null) {
            // Store user in session
            HttpSession session = req.getSession(true);
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("role",   user.getRole());

            response.addProperty("success", true);
            response.addProperty("userId",  user.getUserId());
            response.addProperty("name",    user.getName());
            response.addProperty("role",    user.getRole());
            res.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.addProperty("success", false);
            response.addProperty("message", "Invalid email or password.");
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        res.getWriter().print(response.toString());
    }
}
