package com.example.admin.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
@WebFilter("/*")
public class AuthFilter extends OncePerRequestFilter {

    static final Set<String> publicPaths;

    static {
        publicPaths = new HashSet<>();
        publicPaths.add("/login");
        publicPaths.add("/register");
        publicPaths.add("/vcode");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String url = httpServletRequest.getRequestURI();
        if(publicPaths.contains(url)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String token = httpServletRequest.getHeader("Authorization");
        if(StringUtils.isBlank(token)) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }

    }
}
