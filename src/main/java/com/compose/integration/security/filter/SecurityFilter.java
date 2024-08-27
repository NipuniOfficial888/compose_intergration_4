package com.compose.integration.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(1)
@Slf4j
public class SecurityFilter extends OncePerRequestFilter {

    @Value("${compose.http.auth.token.header.name}")
    String authKey;

    @Value("${compose.http.auth.token}")
    private String authValue;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String value = request.getHeader(authKey);

        // Validate the key and value
        if (authValue.equals(value)) {
            filterChain.doFilter(request, response);
        } else {
            log.error("Unauthorized access");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");

            // Build the JSON response
            String jsonResponse = String.format(
                    "{\"status\": %d, \"error\": \"%s\", \"errorCode\": \"%s\", \"message\": \"%s\"}",
                    HttpServletResponse.SC_UNAUTHORIZED,
                    "Unauthorized",
                    "INVALID_AUTH_TOKEN",
                    "Invalid Token"
            );

            // Write the JSON response
            response.getWriter().write(jsonResponse);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.matches("/api-docs|/api-docs/.*|/version|/swagger-ui/.*|/actuator/.*");
    }
}
