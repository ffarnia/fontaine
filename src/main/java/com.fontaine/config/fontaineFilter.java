package com.fontaine.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Fazel on 12/23/2019.
 */
@Component
public class fontaineFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

//        response.setHeader("Access-Control-Allow-Origin", request.getRemoteAddr() + ":" + request.getRemotePort());
        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8081");
        filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
