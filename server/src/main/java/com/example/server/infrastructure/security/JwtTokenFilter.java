package com.example.server.infrastructure.security;

import com.example.server.infrastructure.exception.RestApiException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author duchieu212
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        String jwtToken = extractJwtToken(request);
        if (request.getRequestURI().endsWith("/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (jwtToken != null) {
            String message = jwtTokenProvider.validateToken(jwtToken);
            if (message.equals("ok")) {
                Authentication authentication = jwtTokenProvider.getAndSetAuthentication(jwtToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else if (message.startsWith("Token refresh:")) {
                int indexStartToken = message.indexOf("Token refresh:");
                Authentication authentication = jwtTokenProvider
                        .getAndSetAuthentication(message.substring(indexStartToken + "Token refresh:".length()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new RestApiException(message);
            }
        } else {
            throw new RestApiException("Hệ thống lỗi, vui lòng đăng nhập lại");
        }
        filterChain.doFilter(request, response);
    }

    private String extractJwtToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
