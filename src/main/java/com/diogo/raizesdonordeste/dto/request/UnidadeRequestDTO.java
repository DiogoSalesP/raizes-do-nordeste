package com.diogo.raizesdonordeste.dto.request;

public record UnidadeRequestDTO(
        String nome,
        String endereco,
        String cidade,
        String estado,
        String telefone
) {
}
