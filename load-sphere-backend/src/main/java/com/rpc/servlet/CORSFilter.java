package com.rpc.servlet;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CORSFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest  httpReq = (HttpServletRequest)  request;
        HttpServletResponse httpRes = (HttpServletResponse) response;

        httpRes.setHeader("Access-Control-Allow-Origin",      "http://localhost:4200");
        httpRes.setHeader("Access-Control-Allow-Credentials", "true");
        httpRes.setHeader("Access-Control-Allow-Methods",     "GET, POST, PUT, DELETE, OPTIONS");
        httpRes.setHeader("Access-Control-Allow-Headers",     "Content-Type, Authorization");

        // Handle preflight OPTIONS request
        if ("OPTIONS".equalsIgnoreCase(httpReq.getMethod())) {
            httpRes.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) {}

    @Override
    public void destroy() {}
}