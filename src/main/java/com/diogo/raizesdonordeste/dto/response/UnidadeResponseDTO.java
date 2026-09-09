package com.diogo.raizesdonordeste.dto.response;

import java.util.UUID;

public record UnidadeResponseDTO(
        UUID idUnidade,
        String nome,
        String endereco,
        String cidade,
        String estado,
        String telefone
) {
}
