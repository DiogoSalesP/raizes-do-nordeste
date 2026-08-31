package com.diogo.raizesdonordeste.dto.request;

import java.util.UUID;

public record ItemPedidoRequestDTO(
        UUID idProduto,
        Integer quantidade,
        String observacao
) {
}
