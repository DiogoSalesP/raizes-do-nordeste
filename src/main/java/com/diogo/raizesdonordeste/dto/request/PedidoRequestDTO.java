package com.diogo.raizesdonordeste.dto.request;

import com.diogo.raizesdonordeste.domain.enums.CanalPedido;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record PedidoRequestDTO(
        @NotNull(message = "campo canalPedido obrigatório")
        CanalPedido canalPedido,

        @NotNull(message = "campo idCliente obrigatório")
        UUID idCliente,

        @NotEmpty(message = "O pedido deve possuir pelo menos um item")
        List<@Valid ItemPedidoRequestDTO> itens
) {
}
