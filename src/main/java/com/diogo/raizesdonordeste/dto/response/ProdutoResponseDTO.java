package com.diogo.raizesdonordeste.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoResponseDTO(
        UUID idProduto,
        String nome,
        String descricao,
        BigDecimal preco,
        Boolean disponivel
) {
}
