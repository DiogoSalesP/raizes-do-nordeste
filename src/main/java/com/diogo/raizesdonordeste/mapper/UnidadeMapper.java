package com.diogo.raizesdonordeste.mapper;

import com.diogo.raizesdonordeste.domain.Unidade;
import com.diogo.raizesdonordeste.dto.request.UnidadeRequestDTO;
import com.diogo.raizesdonordeste.dto.response.UnidadeResponseDTO;

public class UnidadeMapper {

    public static Unidade toEntity(UnidadeRequestDTO dto) {
        Unidade unidade = new Unidade();
        unidade.setNome(dto.nome());
        unidade.setEndereco(dto.endereco());
        unidade.setCidade(dto.cidade());
        unidade.setEstado(dto.estado());
        unidade.setTelefone(dto.telefone());
        return unidade;
    }

    public static UnidadeResponseDTO toResponse(Unidade unidade) {
        return new UnidadeResponseDTO(
                unidade.getIdUnidade(),
                unidade.getNome(),
                unidade.getEndereco(),
                unidade.getCidade(),
                unidade.getEstado(),
                unidade.getTelefone()
        );
    }
}
