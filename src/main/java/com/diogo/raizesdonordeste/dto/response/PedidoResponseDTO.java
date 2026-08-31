package com.diogo.raizesdonordeste.dto.response;

import com.diogo.raizesdonordeste.domain.enums.CanalPedido;
import com.diogo.raizesdonordeste.domain.enums.StatusPedido;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record PedidoResponseDTO(
        UUID idPedido,
        CanalPedido canalPedido,
        StatusPedido statusPedido,
        BigDecimal valorTotal,
        String nomeCliente,
        List<ItemPedidoResponseDTO> itens
) {
}
