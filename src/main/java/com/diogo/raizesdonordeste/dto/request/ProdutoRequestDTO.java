package com.diogo.raizesdonordeste.dto.request;

import java.math.BigDecimal;

public record ProdutoRequestDTO(
        String nome,
        String descricao,
        BigDecimal preco,
        Boolean disponivel
) {
}
