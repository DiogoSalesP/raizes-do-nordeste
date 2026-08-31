package com.diogo.raizesdonordeste.mapper;

import com.diogo.raizesdonordeste.domain.Pedido;
import com.diogo.raizesdonordeste.dto.request.PedidoRequestDTO;
import com.diogo.raizesdonordeste.dto.response.PedidoResponseDTO;

public class PedidoMapper {

    public static Pedido toEntity(PedidoRequestDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setCanalPedido(dto.canalPedido());
        dto.itens().stream().map(ItemPedidoMapper::toEntity).forEach(pedido::adicionaItem);
        return pedido;
    }

    public static PedidoResponseDTO toResponse(Pedido pedido) {
        return new PedidoResponseDTO(
                pedido.getIdPedido(),
                pedido.getCanalPedido(),
                pedido.getStatus(),
                pedido.getValorTotal(),
                pedido.getCliente().getNome(),
                pedido.getItens().stream().map(ItemPedidoMapper::toResponse).toList()
        );
    }
}
