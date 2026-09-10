package com.diogo.raizesdonordeste.dto.response;

import com.diogo.raizesdonordeste.domain.enums.NivelFidelidade;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioResponseDTO(
        UUID idUsuario,
        String nome,
        String email,
        String telefone,
        LocalDateTime cadastro,
        NivelFidelidade nivelFidelidade
) {
}
