package com.diogo.raizesdonordeste.security;

import com.diogo.raizesdonordeste.domain.Usuario;
import com.diogo.raizesdonordeste.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(@NonNull String email) throws RuntimeException{
        Usuario usuario = usuarioService.obterPorLogin(email);
        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado");
        }
        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .roles(usuario.getRoles())
                .build();
    }
}
