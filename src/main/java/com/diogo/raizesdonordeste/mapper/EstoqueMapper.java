package com.diogo.raizesdonordeste.mapper;

import com.diogo.raizesdonordeste.domain.Estoque;
import com.diogo.raizesdonordeste.dto.request.EstoqueRequestDTO;
import com.diogo.raizesdonordeste.dto.response.EstoqueResponseDTO;

public class EstoqueMapper {

    public static Estoque toEntity(EstoqueRequestDTO dto) {
        Estoque estoque = new Estoque();
        estoque.setQuantidade(dto.quantidade());
        estoque.setEstoqueMinimo(dto.estoqueMinimo());
        return estoque;
    }

    public static EstoqueResponseDTO toResponse(Estoque estoque) {
        return new EstoqueResponseDTO(
                estoque.getIdEstoque(),
                estoque.getQuantidade(),
                estoque.getEstoqueMinimo(),
                estoque.getProduto().getNome()
        );
    }
}
