package com.pk.hotelmanagement.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pk.hotelmanagement.users.login.ErrorMessage;
import com.pk.hotelmanagement.users.login.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = httpServletRequest.getHeader("Authorization");
        if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
            try {
                SecurityContextHolder.getContext().setAuthentication(createAuthenticationFromToken(authHeader.substring(7)));
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } catch (JwtException e) {
                httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
                httpServletResponse.getWriter().write(getStatusAsJson());
            }

        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    private UsernamePasswordAuthenticationToken createAuthenticationFromToken(String header) {
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(Token.key).build().parseClaimsJws(header);
        String email = claimsJws.getBody().getSubject();
        String role = claimsJws.getBody().get("role").toString();
        return new UsernamePasswordAuthenticationToken(email, null, Collections.singleton(new SimpleGrantedAuthority(role)));
    }

    private String getStatusAsJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(new ErrorMessage(HttpStatus.UNAUTHORIZED.value(), "Token expired"));
    }

}