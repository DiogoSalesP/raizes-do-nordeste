package com.diogo.raizesdonordeste.dto.request;

import com.diogo.raizesdonordeste.domain.enums.CanalPedido;

import java.util.List;
import java.util.UUID;

public record PedidoRequestDTO(
        CanalPedido canalPedido,
        UUID idCliente,
        List<ItemPedidoRequestDTO> itens
) {
}
