package com.pk.hotelmanagement.users.login;

import com.pk.hotelmanagement.users.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class Token {
    public static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String create(User user) {
        Long now = System.currentTimeMillis();

        return Jwts.builder()
                .setSubject(user.getEmail().toString())
                .claim("role", user.getRole().getName().name())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + 3600000)) // 10 minutes
                .signWith(key).compact();
    }
}
