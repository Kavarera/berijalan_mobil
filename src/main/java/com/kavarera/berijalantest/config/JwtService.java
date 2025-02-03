package com.kavarera.berijalantest.config;

import com.kavarera.berijalantest.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static String SECRET="Bismillah keterima ya allah";

    public <T> T getClaim(String token, Function<Claims,T> claimsFunction){
        final Claims claim = getClaims(token);
        return claimsFunction.apply(claim);
    }

    public String getUsername(String tokenJWT) {
        return getClaim(tokenJWT, Claims::getSubject);
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private static String generateSecretKey() throws NoSuchAlgorithmException {
        MessageDigest d = MessageDigest.getInstance("SHA-256");
        byte[] hash = d.digest(SECRET.getBytes());

        StringBuilder hexHash = new StringBuilder(2*hash.length);
        for (byte b : hash) {
            String a = Integer.toHexString(0xff & b);
            if(a.length() == 1) {
                hexHash.append('0');
            }
            hexHash.append(a);
        }
        return hexHash.toString();
    }

    private Key getSignInKey() {
        byte[] keyBytes = null;
        try {
            keyBytes = Decoders.BASE64.decode(generateSecretKey());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetails user) {
        return generateToken(new HashMap<>(),user);
    }

    private  String generateToken(Map<String,Object> claim,
    UserDetails user){
        return Jwts
                .builder()
                .setClaims(claim)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isValidToken(String token, UserDetails user) {
        final String username  = getUsername(token);
        return (username.equals(user.getUsername())) && !isExpiredToken(token);
    }

    private boolean isExpiredToken(String token) {
        return getExpiration(token).before(new Date());
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }
}
