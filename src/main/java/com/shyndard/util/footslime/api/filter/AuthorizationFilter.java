package com.shyndard.util.footslime.api.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shyndard.util.footslime.api.dao.TokenDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationFilter implements Filter {

    @Autowired
    private TokenDao tokenDao;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String authorization = req.getHeader("Authorization");
        if (req.getRequestURI().startsWith("/matchs/") && authorization != null && authorization.startsWith("Bearer")) {
            if (!tokenDao.isValid(authorization.split(" ")[1])) {
                res.setStatus(HttpServletResponse.SC_FORBIDDEN);
                PrintWriter writer = res.getWriter();
                writer.write("Bad token.");
                writer.close();
                return;
            }
        }
        chain.doFilter(request, response);
    }

}