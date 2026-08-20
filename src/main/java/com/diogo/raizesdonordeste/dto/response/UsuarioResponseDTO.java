package com.diogo.raizesdonordeste.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioResponseDTO(
        UUID idUsuario,
        String nome,
        String email,
        String telefone,
        LocalDateTime cadastro
) {
}
