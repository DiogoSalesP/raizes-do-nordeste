package com.diogo.raizesdonordeste.dto.request;

import com.diogo.raizesdonordeste.domain.enums.StatusPedido;
import jakarta.validation.constraints.NotNull;

public record AtualizarStatusPedidoRequestDTO(
        @NotNull(message = "campo status pedido obrigatório")
        StatusPedido statusPedido
) {
}
