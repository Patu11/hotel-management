package com.pk.hotelmanagement.security;

import com.pk.hotelmanagement.users.Role;
import com.pk.hotelmanagement.users.login.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
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
        if (!authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
            SecurityContextHolder.getContext().setAuthentication(createAuthenticationFromToken(authHeader.substring(7)));
            filterChain.doFilter(httpServletRequest, httpServletResponse);
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
}