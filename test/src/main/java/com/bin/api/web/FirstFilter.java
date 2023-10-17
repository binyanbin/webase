package com.bin.api.web;


import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
@Order(1)
public class FirstFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequestWrapper wrapper = new BinHttpServletRequestWrapper((HttpServletRequest) servletRequest);
        filterChain.doFilter(wrapper, servletResponse);
    }

}