package com.diogo.raizesdonordeste.validator;

import com.diogo.raizesdonordeste.domain.Usuario;
import com.diogo.raizesdonordeste.exception.RegistroDuplicadoException;
import com.diogo.raizesdonordeste.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UsuarioValidator {

    private final UsuarioRepository usuarioRepository;

    public void validarCriar(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            throw new RegistroDuplicadoException("Email já cadastrado!");
        }
    }

    public void validarAtualizar(UUID id, String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null && !usuario.getIdUsuario().equals(id)) {
            throw new RegistroDuplicadoException("Email já cadastrado!");
        }
    }
}
