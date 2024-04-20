package com.example.server.infrastructure.security;
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

import com.example.server.core.common.model.response.CoEmployeesLoginResponse;
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

    public String generateToken(CoEmployeesLoginResponse employee) {
        return Jwts.builder()
                .setSubject(employee.getEmail())
                .claim("id", employee.getId())
                .claim("email", employee.getEmail())
                .claim("role", employee.getRole())
                .claim("name", employee.getLastName())
                .claim("fullName", employee.getFirstName() + " " + employee.getLastName())
                .claim("birthday", employee.getBirthday())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000)))
//                .setExpiration(new Date(System.currentTimeMillis() + (60 * 1000)))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
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
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String email = claims.get("email", String.class);
            CoEmployeesLoginResponse employees = employeesRepository.findEmployeesByEmailToLogin(email)
                    .orElseThrow(() -> new RestApiException(Message.EMAIL_NOT_EXIST));
            if (claims.getExpiration().getTime() < System.currentTimeMillis()) {
                throw new RestApiException("Phiên đăng nhập đã hết hạn, vui lòng đăng nhập lại");
            }
            return "ok";
        } catch (JwtException | IllegalArgumentException e) {
            e.printStackTrace();
            throw new RestApiException("Phiên đăng nhập đã hết hạn, vui lòng đăng nhập lại");
        }
    }
}
