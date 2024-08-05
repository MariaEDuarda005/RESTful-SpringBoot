package com.example.carros.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.carros.api.users.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    // criação de token
    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret); // recebe um secret, para fazer sempre ser um diferente
            String token = JWT.create()
                    .withIssuer("carros") // quem foi o emissor do token
                    .withSubject(user.getLogin()) // usuario que vai receber o token
                    .withExpiresAt(getExpirationData())
                    .sign(algorithm); // fazer a assinatura e a geração final

            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error while generation token ", exception);
        }
    }

    // validação de token
    public String validationToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("carros")
                    .build()
                    .verify(token) // descriptografou o token
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "";
        }
    }

    private Instant getExpirationData(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
