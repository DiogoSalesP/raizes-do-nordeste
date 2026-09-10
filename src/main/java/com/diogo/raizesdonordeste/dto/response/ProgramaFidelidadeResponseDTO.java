package com.diogo.raizesdonordeste.dto.response;

import com.diogo.raizesdonordeste.domain.enums.NivelFidelidade;

import java.util.UUID;

public record ProgramaFidelidadeResponseDTO(
        UUID idFidelidade,
        Integer saldoPontos,
        NivelFidelidade nivel,
        String cliente
) {
}
