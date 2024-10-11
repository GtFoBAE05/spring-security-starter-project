package org.gtfo.learn_spring_security.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gtfo.learn_spring_security.entity.UserAccount;
import org.gtfo.learn_spring_security.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements JwtService {
    @Value("${jwt-secret}")
    private String SECRET_KEY;

    @Value("${jwt-expiration-in-minutes}")
    private Long EXPIRATION_IN_MINUTES;

    @Value("${jwt-issuer}")
    private String ISSUER;

    @Override
    public String generateToken(UserAccount userAccount) {
        log.info("Generating JWT Token for user with id " + userAccount.getId());
        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
            return JWT.create()
                    .withSubject(userAccount.getId())
                    .withIssuer(ISSUER)
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plus(EXPIRATION_IN_MINUTES, ChronoUnit.MINUTES))
                    .sign(algorithm);
        }catch (JWTCreationException e){
            log.error("Error creating token for user with id " + userAccount.getId());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
    }

    @Override
    public String extractSubject(String token) {
        log.info("Verifying JWT Token with token " + token);
        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return decodedJWT.getSubject();
        }catch (JWTVerificationException e){
            log.error("JWT Token for {} is invalid", token);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
    }

    @Override
    public String parseToken(String bearerToken){
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
