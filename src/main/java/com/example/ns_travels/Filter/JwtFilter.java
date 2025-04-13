package com.example.ns_travels.Filter;

import com.example.ns_travels.service.JWTService;
import com.example.ns_travels.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String userName = null;

        // Check if Authorization header contains a Bearer token
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);  // Extract the token from the header
            if (token.isEmpty()) {
                logger.error("JWT token is empty after extraction");
            } else {
                logger.info("Extracted JWT token: " + token);
            }
        } else {
            logger.error("Authorization header is missing or does not start with 'Bearer '");
        }

        // Check if the token is well-formed
        if (token != null && token.split("\\.").length == 3) {
            try {
                userName = jwtService.extractUsername(token);  // Extract username from token
            } catch (Exception e) {
                logger.error("Error extracting username from JWT token", e);
            }
        } else {
            logger.error("JWT token has an invalid format. Token: " + token);
        }

        // If a username is extracted and there is no authentication in the context, authenticate the user
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                // Load user details
                UserDetails userDetails = myUserDetailService.loadUserByUsername(userName);

                // Validate the token
                if (jwtService.validateToken(token, userDetails)) {
                    // If valid, create an authentication token and set it in the context
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);  // Set the authentication object in the SecurityContext
                } else {
                    logger.warn("Invalid JWT token");
                }
            } catch (Exception e) {
                logger.error("Error during JWT validation: ", e);
            }
        }

        filterChain.doFilter(request, response);  // Continue with the next filter in the chain
    }
}
