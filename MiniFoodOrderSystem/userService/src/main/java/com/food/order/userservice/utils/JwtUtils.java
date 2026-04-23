package com.food.order.userservice.utils;

import com.food.order.userservice.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private final String SECRET ="CHUOIBIMAT";
    private final long EXPIRATION = 86400000;

    public String generateToken(User user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId",user.getId())
                .claim("role",user.getRole().name())
                .setIssuedAt( new Date())
                .setExpiration( new Date(System.currentTimeMillis()+ EXPIRATION))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS256)
                .compact();

    }

    public String getUsernameFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(SECRET.getBytes()).build()
                .parseClaimsJws(token ) .getBody().getSubject();
    }
}
