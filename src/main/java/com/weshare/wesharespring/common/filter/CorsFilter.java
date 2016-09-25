package com.weshare.wesharespring.common.filter;

import com.weshare.wesharespring.common.constant.Constant;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter implements Filter {

    FilterConfig filterConfig;

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
        throws IOException, ServletException {

        final HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", Constant.ACCESS_CONTROL_ALLOW_ORIGIN);
        response.setHeader("Access-Control-Allow-Credentials", Constant.ACCESS_CONTROL_ALLOW_CREDENTIALS);
        response.setHeader("Access-Control-Allow-Methods", Constant.ACCESS_CONTROL_ALLOW_METHODS);
        response.setHeader("Access-Control-Max-Age", Constant.ACCESS_CONTROL_MAX_AGE);
        response.setHeader("Access-Control-Allow-Headers", Constant.ACCESS_CONTROL_ALLOW_HEADERS);
        chain.doFilter(req, response);
    }

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        filterConfig.getServletContext().log("Initializing CorsFilter ...");
    }

    @Override
    public void destroy() {
        filterConfig.getServletContext().log("Destroying CorsFilter ...");
    }
}