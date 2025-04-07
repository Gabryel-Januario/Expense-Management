package com.ExpenseManagement.Expense.Management.services;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ExpenseManagement.Expense.Management.dto.LoginRequestDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secretKey;

    public String generateToken(LoginRequestDTO data) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            Date expiresAt = Date.from(Instant.now().plusSeconds(3600));
            
            String token = JWT.create()
                            .withIssuer("expense-Management")
                            .withSubject(data.getLogin())
                            .withExpiresAt(expiresAt)
                            .sign(algorithm);
            
            return token;

        }catch(JWTCreationException e){
            throw new RuntimeException("Error while generating token ", e);
        }
    }

    
}
