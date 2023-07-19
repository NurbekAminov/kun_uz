package com.example.kun_uz.util;

import com.example.kun_uz.dto.JwtDTO;
import com.example.kun_uz.enums.ProfileRole;
import io.jsonwebtoken.*;

import java.util.Date;

public class JWTUtil {
    private static final String secretKey = "!maz234^gikey";
    private static final int tokenLiveTime = 1000 * 3600 * 1; // 1-hour
    public static String encode(Integer profileId, ProfileRole role) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey);

        jwtBuilder.claim("id", profileId);
        jwtBuilder.claim("role", role.toString());

        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (tokenLiveTime)));
        jwtBuilder.setIssuer("kunuz test portali");
        return jwtBuilder.compact();
    }
    public static JwtDTO decode(String token) {
        JwtParser jwtParser = Jwts.parser();
        jwtParser.setSigningKey(secretKey);

        Jws<Claims> jws = jwtParser.parseClaimsJws(token);

        Claims claims = jws.getBody();

        Integer id = (Integer) claims.get("id");

        String role = (String) claims.get("role");
        ProfileRole profileRole = ProfileRole.valueOf(role);

        return new JwtDTO(id, profileRole);
    }
}