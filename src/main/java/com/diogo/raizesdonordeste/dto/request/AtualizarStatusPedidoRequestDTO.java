package com.diogo.raizesdonordeste.dto.request;

import com.diogo.raizesdonordeste.domain.enums.StatusPedido;

public record AtualizarStatusPedidoRequestDTO(
        StatusPedido statusPedido
) {
}
