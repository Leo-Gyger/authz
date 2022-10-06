package org.iata.ecohub.auth_service.authz_server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
public class jwtController {

    @Autowired
    JwtEncoder  encoder;

    @PostMapping("/token")
    public String   token(Authentication    authentication)
    {
        Instant time = Instant.now();
        long expiry_date = 36000L;

        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet    claimsSet = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(time)
                .expiresAt(time.plusSeconds(expiry_date))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

}
