package com.nexdev.jaimedesafio.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class TokenProvider {

    @Value(value = "${jwt.secret}")
    private String secret;

    public String generateToken(UserDetails userDetails) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        System.out.println(secret);
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        date = calendar.getTime();

        return JWT.create()
                .withIssuer("nexdev.desafio")
                .withExpiresAt(date)
                .withSubject(userDetails.getUsername())
                .sign(algorithm);
    }

    public String verifyToken(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        DecodedJWT decodedJWT;

        JWTVerifier verifier = JWT.require(algorithm).withIssuer("nexdev.desafio").build();
        decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }
    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("nexdev.desafio").build();
            DecodedJWT decodedJWT = verifier.verify(token);
            // Verificar a data de expiração do token
            Date expirationDate = decodedJWT.getExpiresAt();
            Calendar calendar = Calendar.getInstance();

            // Verificar se o usuário corresponde ao encaminhado e verificar a data de expiração
            return userDetails.getUsername().equals(decodedJWT.getSubject()) || expirationDate.after(calendar.getTime());
        } catch (Exception e) {
            // O token é inválido ou expirou
            return false;
        }
    }

}
