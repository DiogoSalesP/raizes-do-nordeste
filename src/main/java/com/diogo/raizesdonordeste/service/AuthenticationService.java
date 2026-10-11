package com.diogo.raizesdonordeste.service;

import com.diogo.raizesdonordeste.domain.Usuario;
import com.diogo.raizesdonordeste.dto.request.LoginRequestDTO;
import com.diogo.raizesdonordeste.dto.response.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final long EXPIRATION_MINUTES = 60L;
    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final JwtEncoder jwtEncoder;


    public LoginResponseDTO login(LoginRequestDTO dto) {
        authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(dto.email(), dto.senha())
        );
        Usuario usuario = usuarioService.obterPorLogin(dto.email());
        Instant agora = Instant.now();
        Instant expiraEm = agora.plus(EXPIRATION_MINUTES, ChronoUnit.MINUTES);
        String scope = String.join(" ", usuario.getRoles());

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("raizesdonordeste")
                .subject(usuario.getEmail())
                .issuedAt(agora)
                .expiresAt(expiraEm)
                .claim("scope", scope)
                .build();
        String token = jwtEncoder.encode(
                JwtEncoderParameters.from(JwsHeader.with(() -> "RS256").build(), claims)
        ).getTokenValue();

        return new LoginResponseDTO(token, "Bearer", EXPIRATION_MINUTES * 60);
    }
}
