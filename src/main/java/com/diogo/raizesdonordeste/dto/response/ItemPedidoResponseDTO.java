package com.diogo.raizesdonordeste.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemPedidoResponseDTO(
        UUID idItemPedido,
        String nomeProduto,
        String observacao,
        Integer quantidade,
        BigDecimal precoUnitario
) {
}
