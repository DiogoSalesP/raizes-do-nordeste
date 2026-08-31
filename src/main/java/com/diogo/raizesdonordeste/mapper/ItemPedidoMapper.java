package com.diogo.raizesdonordeste.mapper;

import com.diogo.raizesdonordeste.domain.ItemPedido;
import com.diogo.raizesdonordeste.domain.Produto;
import com.diogo.raizesdonordeste.dto.request.ItemPedidoRequestDTO;
import com.diogo.raizesdonordeste.dto.response.ItemPedidoResponseDTO;

public class ItemPedidoMapper {

    public static ItemPedido toEntity(ItemPedidoRequestDTO dto) {
        ItemPedido itemPedido = new ItemPedido();
        Produto produto = new Produto();
        produto.setIdProduto(dto.idProduto());
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(dto.quantidade());
        itemPedido.setObservacao(dto.observacao());
        return itemPedido;
    }

    public static ItemPedidoResponseDTO toResponse(ItemPedido itemPedido) {
        return new ItemPedidoResponseDTO(
                itemPedido.getIdItemPedido(),
                itemPedido.getProduto().getNome(),
                itemPedido.getObservacao(),
                itemPedido.getQuantidade(),
                itemPedido.getPrecoUnitario()
        );

    }
}
