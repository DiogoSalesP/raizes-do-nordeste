package com.diogo.raizesdonordeste.dto.request;

public record UsuarioRequestDTO(
        String nome,
        String email,
        String senha,
        String telefone
) {
}
