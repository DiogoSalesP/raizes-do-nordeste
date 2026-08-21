package com.diogo.raizesdonordeste.mapper;

import com.diogo.raizesdonordeste.domain.Produto;
import com.diogo.raizesdonordeste.dto.request.ProdutoRequestDTO;
import com.diogo.raizesdonordeste.dto.response.ProdutoResponseDTO;

public class ProdutoMapper {

    public static Produto toEntity(ProdutoRequestDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setPreco(dto.preco());
        produto.setDisponivel(dto.disponivel());
        return produto;
    }

    public static ProdutoResponseDTO toResponse(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getIdProduto(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getDisponivel()
        );
    }
}
