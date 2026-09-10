package com.diogo.raizesdonordeste.dto.response;

import java.util.UUID;

public record EstoqueResponseDTO(
        UUID idEstoque,
        Integer quantidade,
        Integer estoqueMinimo,
        String produto
) {
}
