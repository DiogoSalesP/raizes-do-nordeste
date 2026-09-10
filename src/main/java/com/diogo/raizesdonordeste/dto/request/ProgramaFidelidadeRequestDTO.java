package com.diogo.raizesdonordeste.dto.request;

import com.diogo.raizesdonordeste.domain.enums.NivelFidelidade;

public record ProgramaFidelidadeRequestDTO(
        Integer saldoPontos,
        NivelFidelidade nivel
) {
}
