package com.diogo.raizesdonordeste.dto.request;

import java.util.UUID;

public record EstoqueRequestDTO(
        Integer quantidade,
        Integer estoqueMinimo,
        UUID idProduto
) {
}
