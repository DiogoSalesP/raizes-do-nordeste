package com.diogo.raizesdonordeste.mapper;

import com.diogo.raizesdonordeste.domain.ProgramaFidelidade;
import com.diogo.raizesdonordeste.dto.request.ProgramaFidelidadeRequestDTO;
import com.diogo.raizesdonordeste.dto.response.ProgramaFidelidadeResponseDTO;

public class ProgramaFidelidadeMapper {

    public static ProgramaFidelidade toEntity(ProgramaFidelidadeRequestDTO dto) {
        ProgramaFidelidade fidelidade = new ProgramaFidelidade();
        fidelidade.setSaldoPontos(dto.saldoPontos());
        fidelidade.setNivel(dto.nivel());
        return fidelidade;
    }

    public static ProgramaFidelidadeResponseDTO toResponse(ProgramaFidelidade fidelidade) {
        return new ProgramaFidelidadeResponseDTO(
                fidelidade.getIdFidelidade(),
                fidelidade.getSaldoPontos(),
                fidelidade.getNivel(),
                fidelidade.getUsuario().getNome()
        );
    }
}
