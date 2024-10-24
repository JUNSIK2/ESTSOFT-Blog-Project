package com.estsoft.springproject.filter;

import jakarta.servlet.*;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class FirstFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("FirstFilter init()");
    }

    @Override
    public void destroy() {
        System.out.println("FirstFilter destroy()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("FirstFilter doFilter() request");

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();
        System.out.println(requestURI + " on FirstFilter");

        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("FirstFilter doFilter() response");
    }
}
