package com.example.server.infrastructure.security;//package com.example.employeesserver.infrastructure.security;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.SignatureException;
//import io.jsonwebtoken.UnsupportedJwtException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.io.Serializable;
//import java.util.Date;
//
///**
// * @author duchieu212
// */
//@Component
//public class JwtUtility implements Serializable {
//
//    private static final Logger logger = LoggerFactory.getLogger(JwtUtility.class);
//
//    @Value("${identity.secretKey}")
//    private String jwtSecret = "secretkey";
//
//    public String generateJwtToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date((new Date()).getTime() + (100 * 60 * 60 * 24)))
//                .signWith(SignatureAlgorithm.HS512, jwtSecret)
//                .compact();
//    }
//
////    public String getUserNameFromJwtToken(String token) {
////        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
////    }
//
//    public boolean validateJwtToken(String authToken) {
//        try {
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
//            return true;
//        } catch (SignatureException e) {
//            logger.error("Invalid JWT signature: {}", e.getMessage());
//        } catch (MalformedJwtException e) {
//            logger.error("Invalid JWT token: {}", e.getMessage());
//        } catch (ExpiredJwtException e) {
//            logger.error("JWT token is expired: {}", e.getMessage());
//        } catch (UnsupportedJwtException e) {
//            logger.error("JWT token is unsupported: {}", e.getMessage());
//        } catch (IllegalArgumentException e) {
//            logger.error("JWT claims string is empty: {}", e.getMessage());
//        }
//        return false;
//    }
//
//}

import com.example.server.entity.Employees;
import com.example.server.infrastructure.constant.Message;
import com.example.server.infrastructure.exception.RestApiException;
import com.example.server.repositoty.EmployeesRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${identity.secretKey}")
    private String SECRET_KEY;

    private final EmployeesRepository employeesRepository;

    public JwtTokenProvider(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    public String generateToken(Employees employee) {
        String token = Jwts.builder()
                .setSubject(employee.getEmail())
                .claim("id", employee.getId())
                .claim("email", employee.getEmail())
                .claim("role", employee.getRole())
                .claim("name", employee.getLastName())
                .claim("fullName", employee.getFirstName() + " " + employee.getLastName())
                .claim("birthday", employee.getBirthday())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000)))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
        return token;
    }


    public Authentication getAndSetAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        String role = claims.get("role", String.class);
        SimpleGrantedAuthority authorities = new SimpleGrantedAuthority(role);
        return new UsernamePasswordAuthenticationToken(claims.get("email"), token, Collections.singletonList(authorities));
    }

    public String validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
//                    .setAllowedClockSkewSeconds(3600)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String email = claims.get("email", String.class);
            Employees employees = employeesRepository.findByEmail(email)
                    .orElseThrow(() -> new RestApiException(Message.EMAIL_NOT_EXIST));
            if (claims.getExpiration().getTime() < System.currentTimeMillis()) {
                return ("Token refresh:" + generateToken(employees));
            }
            return "ok";
        } catch (JwtException | IllegalArgumentException e) {
            e.printStackTrace();
            throw new RestApiException("Phiên đăng nhập đã hết hạn, vui lòng đăng nhập lại");
        }
    }
}
