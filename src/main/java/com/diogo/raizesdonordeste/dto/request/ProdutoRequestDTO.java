package com.diogo.raizesdonordeste.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoRequestDTO(
        String nome,
        String descricao,
        BigDecimal preco,
        Boolean disponivel,
        UUID idUnidade
) {
}
