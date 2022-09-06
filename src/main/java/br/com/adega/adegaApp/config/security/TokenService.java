package br.com.adega.adegaApp.config.security;

import br.com.adega.adegaApp.entities.Funcionario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${adega.jwt.expiration}")
    private String expiration;

    @Value("${adega.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {
        Funcionario funcionario = (Funcionario) authentication.getPrincipal();
        return Jwts.builder()
                .setIssuer("API Adega")
                .setSubject(funcionario.getIdFuncionario().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + Long.parseLong(expiration)))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
