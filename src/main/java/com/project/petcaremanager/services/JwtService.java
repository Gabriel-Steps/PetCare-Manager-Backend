package com.project.petcaremanager.services;

import com.project.petcaremanager.models.User;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class JwtService {
    private final JwtEncoder jwtEncoder;

    public JwtService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(User user) {

        Instant now = Instant.now();

        String role = user.getRole().name();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("todolist-api")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(3600))
                .subject(user.getEmail())
                .claim("userId", user.getId())
                .claim("roles", List.of(role))
                .build();

        JwsHeader headers = JwsHeader.with(MacAlgorithm.HS256).build();

        return jwtEncoder.encode(
                JwtEncoderParameters.from(headers, claims)
        ).getTokenValue();
    }
}
