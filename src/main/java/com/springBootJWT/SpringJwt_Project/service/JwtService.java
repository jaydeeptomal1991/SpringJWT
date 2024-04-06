package com.springBootJWT.SpringJwt_Project.service;

import com.springBootJWT.SpringJwt_Project.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    public final String SECRET_KEY="191b25e31ff8a0d2183be5d90736c74a96190bebb01e5fb359f1754d956bb017";

    @Value("${app.jwtExpirationInMs}")
    private Long jwtExpirationTime;

    public String generateToken(User user){
        Date now=new Date();
        Date expiry=new Date(now.getTime()+ jwtExpirationTime);
        String token= Jwts
                    .builder()
                    .subject(user.getUsername())
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(expiry)
                    .signWith(getSignInKey())
                    .compact();
        return token;
    }

    private SecretKey getSignInKey(){
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    // It will extract all the payload from a token
    public Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T extractClaims(String token, Function<Claims,T>resolver){
        Claims claims=extractAllClaims(token);
        return resolver.apply(claims);
    }

    public String extractUserName(String token){
        return extractClaims(token,Claims::getSubject);
    }

    public boolean isValid(String token, UserDetails user){
        String userName=extractUserName(token);
        return (userName.equals(user.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }
}
